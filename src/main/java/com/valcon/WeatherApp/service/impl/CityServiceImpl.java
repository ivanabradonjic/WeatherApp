package com.valcon.WeatherApp.service.impl;

import com.valcon.WeatherApp.dto.CityResponseDTO;
import com.valcon.WeatherApp.mapper.CityMapper;
import com.valcon.WeatherApp.model.City;
import com.valcon.WeatherApp.repository.CityRepository;
import com.valcon.WeatherApp.service.CityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City getByName(String name) {
        return cityRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("City with name"+name+"hasn't been found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CityResponseDTO> getAll() {
        return  getAllCities()
                .stream()
                .map(CityMapper::mapToDTO)
                .toList();
    }
    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    @Override
    public City getById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("City with id = "+ id+"hasn't been found"));
    }
}
