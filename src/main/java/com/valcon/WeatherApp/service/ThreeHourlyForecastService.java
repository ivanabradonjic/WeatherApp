package com.valcon.WeatherApp.service;

import com.valcon.WeatherApp.dto.OwmThreeHourlyForecastDTO;
import com.valcon.WeatherApp.model.FiveDaysForecast;
import com.valcon.WeatherApp.model.ThreeHourlyForecast;

public interface ThreeHourlyForecastService {
    public void saveOne(OwmThreeHourlyForecastDTO owmThreeHourlyForecastDTO, FiveDaysForecast fiveDaysForecast);

}
