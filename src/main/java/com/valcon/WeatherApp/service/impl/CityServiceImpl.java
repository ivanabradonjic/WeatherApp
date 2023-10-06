package com.valcon.WeatherApp.service.impl;

import com.valcon.WeatherApp.dto.CityResponseDTO;
import com.valcon.WeatherApp.mapper.CityMapper;
import com.valcon.WeatherApp.repository.CityRepository;
import com.valcon.WeatherApp.service.CityService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CityResponseDTO> getAll() {
        return cityRepository.findAll()
                .stream()
                .map(CityMapper::mapToDTO)
                .toList();
    }
}
