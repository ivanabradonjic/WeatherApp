package com.valcon.WeatherApp.service;

import com.valcon.WeatherApp.dto.CityResponseDTO;

import java.util.List;

public interface CityService {

    List<CityResponseDTO> getAll();
}
