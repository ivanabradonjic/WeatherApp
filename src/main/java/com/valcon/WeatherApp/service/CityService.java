package com.valcon.WeatherApp.service;

import com.valcon.WeatherApp.dto.CityResponseDTO;
import com.valcon.WeatherApp.model.City;

import java.util.List;

public interface CityService {
    City getByName(String name);

    List<CityResponseDTO> getAll();
    List<City> getAllCities();

    CityResponseDTO getById(Long id);

    City getCityById(Long cityId);
}
