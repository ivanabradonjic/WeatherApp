package com.valcon.WeatherApp.service.impl;

import com.valcon.WeatherApp.dto.CityResponseDTO;
import com.valcon.WeatherApp.exception.ResourceNotFoundException;
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
        City foundedCity = cityRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("City with name"+name+"hasn't been found"));
        return foundedCity;
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
    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City with id = "+ id+"hasn't been found"));
    }

    public CityResponseDTO getById(Long id){
        return CityMapper.mapToDTO(getCityById(id));
    }
}
