package com.valcon.WeatherApp.mapper;

import com.valcon.WeatherApp.dto.OwmThreeHourlyForecastDTO;
import com.valcon.WeatherApp.model.ThreeHourlyForecast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ThreeHourlyForecastMapper {
    private ThreeHourlyForecastMapper() {
    }

    public static ThreeHourlyForecast mapToEntity(OwmThreeHourlyForecastDTO owmThreeHourlyForecastDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        double temp = owmThreeHourlyForecastDTO.getMain().getTemp();
        LocalDateTime dateTime = LocalDateTime.parse(owmThreeHourlyForecastDTO.getDateTime(), formatter);
        return new ThreeHourlyForecast(temp, dateTime);
    }
}
