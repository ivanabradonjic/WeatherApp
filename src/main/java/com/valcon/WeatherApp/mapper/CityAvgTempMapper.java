package com.valcon.WeatherApp.mapper;

import com.valcon.WeatherApp.dto.CityAvgTempResponseDTO;

public final class CityAvgTempMapper {
    private CityAvgTempMapper() {
    }
    public static CityAvgTempResponseDTO mapToDTO(String cityName, double cityAvgTemp){
        return new CityAvgTempResponseDTO(cityName,cityAvgTemp);
    }
}
