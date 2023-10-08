package com.valcon.WeatherApp.service.impl;

import com.valcon.WeatherApp.model.City;
import com.valcon.WeatherApp.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        Assertions.assertEquals(city,result);
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
    }

    @Test
    void getAllCities() {
    }

    @Test
    void getById() {
    }
}