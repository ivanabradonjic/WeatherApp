package com.valcon.WeatherApp.service.impl;

import com.valcon.WeatherApp.comparator.AvgTempComparator;
import com.valcon.WeatherApp.dto.CityAvgTempResponseDTO;
import com.valcon.WeatherApp.dto.TimeIntervalParametersDTO;
import com.valcon.WeatherApp.dto.OwmFiveDaysForecastDTO;
import com.valcon.WeatherApp.dto.OwmThreeHourlyForecastDTO;
import com.valcon.WeatherApp.mapper.CityAvgTempMapper;
import com.valcon.WeatherApp.model.City;
import com.valcon.WeatherApp.model.FiveDaysForecast;
import com.valcon.WeatherApp.repository.FiveDaysForecastRepository;
import com.valcon.WeatherApp.service.CityService;
import com.valcon.WeatherApp.service.FiveDaysForecastService;
import com.valcon.WeatherApp.service.ThreeHourlyForecastService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FiveDaysForecastServiceImpl implements FiveDaysForecastService {
    @Value("${openweathermap.api.key}")
    private String apiKey;
    private final String apiUrl = "https://api.openweathermap.org/data/2.5/forecast";

    private final WebClient.Builder webClientBuilder;
    private final CityService cityService;
    private final FiveDaysForecastRepository fiveDaysForecastRepository;

    private final ThreeHourlyForecastService threeHourlyForecastService;

    public FiveDaysForecastServiceImpl(WebClient.Builder webClientBuilder, CityService cityService, FiveDaysForecastRepository fiveDaysForecastRepository, ThreeHourlyForecastService threeHourlyForecastService) {
        this.webClientBuilder = webClientBuilder;
        this.cityService = cityService;
        this.fiveDaysForecastRepository = fiveDaysForecastRepository;
        this.threeHourlyForecastService = threeHourlyForecastService;
    }

    public OwmFiveDaysForecastDTO getFiveDaysForecastFromOwm(String city) {
        return webClientBuilder.build()
                .get()
                .uri(apiUrl + "?q={city}&appid={apiKey}", city, apiKey)
                .retrieve()
                .bodyToMono(OwmFiveDaysForecastDTO.class)
                .block();
    }

    @Transactional
    @PostConstruct
    public void saveAll() {

        List<City> allCities = cityService.getAllCities();

        for (City city : allCities) {

            OwmFiveDaysForecastDTO owmFiveDaysForecastDTO = getFiveDaysForecastFromOwm(city.getName());

            saveOne(owmFiveDaysForecastDTO, city);


        }
    }

    @Transactional
    public void saveOne(OwmFiveDaysForecastDTO owmFiveDaysForecastDTO, City city) {

        FiveDaysForecast fiveDaysForecast = new FiveDaysForecast(city);
        fiveDaysForecastRepository.save(fiveDaysForecast);
        for (OwmThreeHourlyForecastDTO owmThreeHourlyForecastDTO : owmFiveDaysForecastDTO.getOwmThreeHourlyForecastDTOList()) {

            threeHourlyForecastService.saveOne(owmThreeHourlyForecastDTO, fiveDaysForecast);
        }

    }

    public List<CityAvgTempResponseDTO> allCityAverageTemperatures(TimeIntervalParametersDTO timeIntervalParametersDTO) {
        List<CityAvgTempResponseDTO> allCitiesAvgTempResponseDTO = new ArrayList<>();
        List<City> allCities = cityService.getAllCities();
        double averageTemperature = 0.0;
        for (City city : allCities) {
            CityAvgTempResponseDTO cityAvgTempResponseDTO = averageTemperatureByCity(city.getName(), timeIntervalParametersDTO);
            allCitiesAvgTempResponseDTO.add(cityAvgTempResponseDTO);

        }
        Collections.sort(allCitiesAvgTempResponseDTO, new AvgTempComparator());
        return allCitiesAvgTempResponseDTO;

    }

    public CityAvgTempResponseDTO averageTemperatureByCity(String name, TimeIntervalParametersDTO timeIntervalParametersDTO) {
        City foundedCity = cityService.getByName(name);
        FiveDaysForecast foundedFiveDaysForecast = fiveDaysForecastRepository.findByCity(foundedCity);
        LocalDateTime startDateTime = timeIntervalParametersDTO.getStartDateTime();
        LocalDateTime endDateTime = timeIntervalParametersDTO.getEndDateTime();

        int cityAvgTemp = threeHourlyForecastService.averageTemperatureInInterval(foundedFiveDaysForecast, startDateTime, endDateTime);
        return CityAvgTempMapper.mapToDTO(foundedCity.getName(), cityAvgTemp);
    }


}
