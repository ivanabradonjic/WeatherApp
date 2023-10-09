package com.valcon.WeatherApp.service.impl;

import com.valcon.WeatherApp.dto.CityResponseDTO;
import com.valcon.WeatherApp.mapper.CityMapper;
import com.valcon.WeatherApp.model.City;
import com.valcon.WeatherApp.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityServiceImpl;


    @Test
    void getByName() {
        City city = new City();
        city.setName("Belgrade");
        when(cityRepository.findByName("Belgrade")).thenReturn(Optional.of(city));

        City result = cityServiceImpl.getByName("Belgrade");
        Assertions.assertEquals(city, result);
    }

    @Test
    void getByName_WhenCityNameNotFound_ShouldThrowException() {

        when(cityRepository.findByName("Valjevo")).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cityServiceImpl.getByName("Valjevo");
        });
    }


    @Test
    void getAll() {
        City city1 = new City("Belgrade", "sr");
        City city2 = new City("Novi Sad", "sr");
        List<City> cities = Arrays.asList(city1, city2);
        when(cityServiceImpl.getAllCities()).thenReturn(cities);
        mockStatic(CityMapper.class);

        CityResponseDTO dto1 = new CityResponseDTO(Long.valueOf(1), "Belgrade");
        CityResponseDTO dto2 = new CityResponseDTO(Long.valueOf(2), "Novi Sad");
        when(CityMapper.mapToDTO(city1)).thenReturn(dto1);
        when(CityMapper.mapToDTO(city2)).thenReturn(dto2);

        List<CityResponseDTO> result = cityServiceImpl.getAll();

        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }


    @Test
    void getAllCities() {
        City city1 = new City("Belgrade", "sr");
        City city2 = new City("Novi Sad", "sr");
        List<City> cities = Arrays.asList(city1, city2);
        when(cityRepository.findAll()).thenReturn(cities);
        List<City> result = cityServiceImpl.getAllCities();
        assertEquals(2, result.size()); // Assuming there are 2 cities in the list
        assertEquals("Belgrade", result.get(0).getName());
        assertEquals("Novi Sad", result.get(1).getName());
    }

    @Test
    void getAllCities_WhenCitiesNotFound_ShouldThrowException() {

        when(cityRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cityServiceImpl.getAll();
        });
    }


    @Test
    void getById() {
        City city = new City();
        city.setId(Long.valueOf(1));
        when(cityRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(city));

        City result = cityServiceImpl.getCityById(Long.valueOf(1));
        Assertions.assertEquals(city, result);
    }

    @Test
    void getById_WhenCityIdNotFound_ShouldThrowException() {

        when(cityRepository.findById(Long.valueOf(4))).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cityServiceImpl.getById(Long.valueOf(4));
        });
    }

}