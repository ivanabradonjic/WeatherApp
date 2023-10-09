package com.valcon.WeatherApp.service;

import com.valcon.WeatherApp.dto.OwmThreeHourlyForecastDTO;
import com.valcon.WeatherApp.model.FiveDaysForecast;
import com.valcon.WeatherApp.model.ThreeHourlyForecast;

import java.time.LocalDateTime;

public interface ThreeHourlyForecastService {
    public void saveOne(OwmThreeHourlyForecastDTO owmThreeHourlyForecastDTO, FiveDaysForecast fiveDaysForecast);
    public int averageTemperatureInInterval(FiveDaysForecast fiveDaysForecast, LocalDateTime startDateTime, LocalDateTime endDateTime);

}
