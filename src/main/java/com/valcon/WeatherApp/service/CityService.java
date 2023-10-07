package com.valcon.WeatherApp.service;

import com.valcon.WeatherApp.dto.CityResponseDTO;
import com.valcon.WeatherApp.model.City;

import java.util.List;

public interface CityService {

    List<CityResponseDTO> getAll();
    List<City> getAllCities();
}
