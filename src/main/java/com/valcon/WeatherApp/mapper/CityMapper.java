package com.valcon.WeatherApp.mapper;

import com.valcon.WeatherApp.dto.CityResponseDTO;
import com.valcon.WeatherApp.model.City;

public final class CityMapper {
    private CityMapper() {
    }

    public static CityResponseDTO mapToDTO(City city) {
        return new CityResponseDTO(city.getId(),city.getName());
    }
}
