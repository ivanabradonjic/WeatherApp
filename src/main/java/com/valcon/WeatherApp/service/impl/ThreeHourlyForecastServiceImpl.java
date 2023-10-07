package com.valcon.WeatherApp.service.impl;

import com.valcon.WeatherApp.dto.OwmThreeHourlyForecastDTO;
import com.valcon.WeatherApp.mapper.ThreeHourlyForecastMapper;
import com.valcon.WeatherApp.model.FiveDaysForecast;
import com.valcon.WeatherApp.model.ThreeHourlyForecast;
import com.valcon.WeatherApp.repository.ThreeHourlyForecastRepository;
import com.valcon.WeatherApp.service.ThreeHourlyForecastService;
import org.springframework.stereotype.Service;

@Service
public class ThreeHourlyForecastServiceImpl implements ThreeHourlyForecastService {
    private final ThreeHourlyForecastRepository threeHourlyForecastRepository;

    public ThreeHourlyForecastServiceImpl(ThreeHourlyForecastRepository threeHourlyForecastRepository) {
        this.threeHourlyForecastRepository = threeHourlyForecastRepository;
    }

    public void saveOne(OwmThreeHourlyForecastDTO owmThreeHourlyForecastDTO, FiveDaysForecast fiveDaysForecast) {

        /*double temp = owmThreeHourlyForecastDTO.getMain().getTemp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(owmThreeHourlyForecastDTO.getDateTime(), formatter);
        ThreeHourlyForecast threeHourlyForecast = new ThreeHourlyForecast(temp,dateTime,fiveDaysForecast);*/
        //       fiveDaysForecast.getThreeHourlyForecasts().add(threeHourlyForecast);
        ThreeHourlyForecast threeHourlyForecast = ThreeHourlyForecastMapper.mapToEntity(owmThreeHourlyForecastDTO);
        threeHourlyForecast.setFiveDaysForecast(fiveDaysForecast);

        threeHourlyForecastRepository.save(threeHourlyForecast);

    }

}
