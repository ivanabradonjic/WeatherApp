package com.valcon.WeatherApp.service.impl;

import com.valcon.WeatherApp.comparator.AvgTempComparator;
import com.valcon.WeatherApp.dto.*;
import com.valcon.WeatherApp.mapper.CityAvgTempMapper;
import com.valcon.WeatherApp.model.City;
import com.valcon.WeatherApp.model.FiveDaysForecast;
import com.valcon.WeatherApp.repository.CityRepository;
import com.valcon.WeatherApp.repository.FiveDaysForecastRepository;
import com.valcon.WeatherApp.service.ThreeHourlyForecastService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FiveDaysForecastServiceImplTest {
    @Mock
    private CityRepository cityRepository;
    @Mock
    private ThreeHourlyForecastService threeHourlyForecastService;
    @Mock
    private FiveDaysForecastRepository fiveDaysForecastRepository;
    @Mock
    private WebClient.Builder webClientBuilder;
    @Mock
    private CityServiceImpl cityServiceImpl;
    @Mock
    AvgTempComparator avgTempComparator;

    @InjectMocks
    private FiveDaysForecastServiceImpl fiveDaysForecastServiceImpl;


    @Test
    void getFiveDaysForecastFromOwm() {

        String city = "Belgrade";
        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast";
        String apiKey = "e6b3111d288c97341634e09c15baabeb";

        OwmFiveDaysForecastDTO expectedForecast = new OwmFiveDaysForecastDTO();
        List<OwmThreeHourlyForecastDTO> threeHourlyForecastDTOList = new ArrayList<>();
        threeHourlyForecastDTOList.add(new OwmThreeHourlyForecastDTO(new MainInfoDTO(289.38), "2023-10-10 06:00:00"));
        threeHourlyForecastDTOList.add(new OwmThreeHourlyForecastDTO(new MainInfoDTO(292.85), "2023-10-10 09:00:00"));
        threeHourlyForecastDTOList.add(new OwmThreeHourlyForecastDTO(new MainInfoDTO(293.85), "2023-10-10 12:00:00"));
        expectedForecast.setOwmThreeHourlyForecastDTOList(threeHourlyForecastDTOList);

        expectedForecast.setOwmThreeHourlyForecastDTOList(new ArrayList<>());


        WebClient webClient = WebClient.builder().build();
        when(webClientBuilder.build()).thenReturn(webClient);


        when(webClient.get()
                .uri(apiUrl + "?q={city}&appid={apiKey}", city, apiKey)
                .retrieve()
                .bodyToMono(OwmFiveDaysForecastDTO.class))
                .thenReturn(Mono.just(expectedForecast));


        OwmFiveDaysForecastDTO result = fiveDaysForecastServiceImpl.getFiveDaysForecastFromOwm(city);

        assertEquals(expectedForecast, result);
    }


    @Test
    void saveOne() {
        OwmFiveDaysForecastDTO owmFiveDaysForecastDTO = new OwmFiveDaysForecastDTO();

        City city = new City("Belgrade", "sr");

        List<OwmThreeHourlyForecastDTO> threeHourlyForecastDTOList = new ArrayList<>();
        threeHourlyForecastDTOList.add(new OwmThreeHourlyForecastDTO(new MainInfoDTO(289.38), "2023-10-10 06:00:00"));
        threeHourlyForecastDTOList.add(new OwmThreeHourlyForecastDTO(new MainInfoDTO(292.85), "2023-10-10 09:00:00"));
        threeHourlyForecastDTOList.add(new OwmThreeHourlyForecastDTO(new MainInfoDTO(293.85), "2023-10-10 12:00:00"));

        owmFiveDaysForecastDTO.setOwmThreeHourlyForecastDTOList(threeHourlyForecastDTOList);

        FiveDaysForecast savedFiveDaysForecast = new FiveDaysForecast(city);
        when(fiveDaysForecastRepository.save(any(FiveDaysForecast.class))).thenReturn(savedFiveDaysForecast);

        fiveDaysForecastServiceImpl.saveOne(owmFiveDaysForecastDTO, city);

        verify(fiveDaysForecastRepository, times(1)).save(any(FiveDaysForecast.class));
        verify(threeHourlyForecastService, times(threeHourlyForecastDTOList.size())).saveOne(any(OwmThreeHourlyForecastDTO.class), any(FiveDaysForecast.class));
    }

    @Test
    void averageTemperatureByCity() {
        String cityName = "Belgrade";

        LocalDateTime startDateTime = LocalDateTime.now().plusHours(3);
        LocalDateTime endDateTime = LocalDateTime.now().plusHours(6);

        City city = new City("Belgrade", "sr");
        FiveDaysForecast fiveDaysForecast = new FiveDaysForecast(city);
        TimeIntervalParametersDTO timeIntervalParametersDTO = new TimeIntervalParametersDTO(startDateTime, endDateTime);
        int expectedAvgTemp = 14;

        mockStatic(CityAvgTempMapper.class);

        when(cityServiceImpl.getByName(cityName)).thenReturn(city);
        when(fiveDaysForecastRepository.findByCity(city)).thenReturn(fiveDaysForecast);
        when(threeHourlyForecastService.averageTemperatureInInterval(fiveDaysForecast, startDateTime, endDateTime)).thenReturn(expectedAvgTemp);
        when(CityAvgTempMapper.mapToDTO(city.getName(), expectedAvgTemp)).thenReturn(new CityAvgTempResponseDTO(city.getName(), expectedAvgTemp));

        CityAvgTempResponseDTO result = fiveDaysForecastServiceImpl.averageTemperatureByCity(cityName, timeIntervalParametersDTO);

        assertEquals(city.getName(), result.getCityName());
        assertEquals(expectedAvgTemp, result.getCityAvgTemp());
    }


    @Test
    void allCityAverageTemperatures() {
        LocalDateTime startDateTime = LocalDateTime.now().plusHours(3);
        LocalDateTime endDateTime = LocalDateTime.now().plusHours(6);
        TimeIntervalParametersDTO timeIntervalParametersDTO = new TimeIntervalParametersDTO(startDateTime, endDateTime);
        City city2 = new City("Belgrade", "sr");
        City city3 = new City("London", "uk");
        City city1 = new City("Novi Sad", "sr");

        FiveDaysForecast fiveDaysForecast1 = new FiveDaysForecast(city1);
        FiveDaysForecast fiveDaysForecast2 = new FiveDaysForecast(city2);
        FiveDaysForecast fiveDaysForecast3 = new FiveDaysForecast(city3);


        CityAvgTempResponseDTO city1AvgTempResponse = new CityAvgTempResponseDTO("Novi Sad", 14);
        CityAvgTempResponseDTO city2AvgTempResponse = new CityAvgTempResponseDTO("Belgrade", 14);
        CityAvgTempResponseDTO city3AvgTempResponse = new CityAvgTempResponseDTO("London", 17);

        List<City> cities = Arrays.asList(city1, city2, city3);
        when(cityServiceImpl.getAllCities()).thenReturn(cities);

        when(fiveDaysForecastRepository.findByCity(city1)).thenReturn(fiveDaysForecast1);
        when(fiveDaysForecastRepository.findByCity(city2)).thenReturn(fiveDaysForecast2);
        when(fiveDaysForecastRepository.findByCity(city3)).thenReturn(fiveDaysForecast3);

        when(threeHourlyForecastService.averageTemperatureInInterval(fiveDaysForecast1, startDateTime, endDateTime)).thenReturn(14);
        when(threeHourlyForecastService.averageTemperatureInInterval(fiveDaysForecast2, startDateTime, endDateTime)).thenReturn(14);
        when(threeHourlyForecastService.averageTemperatureInInterval(fiveDaysForecast3, startDateTime, endDateTime)).thenReturn(17);

        when(fiveDaysForecastServiceImpl.averageTemperatureByCity(eq("Novi Sad"), any(TimeIntervalParametersDTO.class))).thenReturn(city1AvgTempResponse);
        when(fiveDaysForecastServiceImpl.averageTemperatureByCity(eq("Belgrade"), any(TimeIntervalParametersDTO.class))).thenReturn(city2AvgTempResponse);
        when(fiveDaysForecastServiceImpl.averageTemperatureByCity(eq("London"), any(TimeIntervalParametersDTO.class))).thenReturn(city3AvgTempResponse);


        List<CityAvgTempResponseDTO> result = fiveDaysForecastServiceImpl.allCityAverageTemperatures(timeIntervalParametersDTO);

        assertNotNull(result);
        assertEquals(3, result.size());

        assertTrue(result.get(0).getCityAvgTemp() <= result.get(1).getCityAvgTemp());


    }


}