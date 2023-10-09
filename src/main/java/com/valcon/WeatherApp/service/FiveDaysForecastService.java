package com.valcon.WeatherApp.service;

import com.valcon.WeatherApp.dto.CityAvgTempResponseDTO;
import com.valcon.WeatherApp.dto.TimeIntervalParametersDTO;

import java.util.List;

public interface FiveDaysForecastService {

    public CityAvgTempResponseDTO averageTemperatureByCity(String name, TimeIntervalParametersDTO timeIntervalParametersDTO);
    public List<CityAvgTempResponseDTO> allCityAverageTemperatures(TimeIntervalParametersDTO timeIntervalParametersDTO);

}
