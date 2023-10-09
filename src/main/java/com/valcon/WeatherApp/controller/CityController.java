package com.valcon.WeatherApp.controller;

import com.valcon.WeatherApp.controller.api.CityControllerApi;
import com.valcon.WeatherApp.dto.CityResponseDTO;
import com.valcon.WeatherApp.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cities")
public class CityController implements CityControllerApi {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<CityResponseDTO> getAll() {
        return cityService.getAll();
    }

    @GetMapping("/{id}")
    public CityResponseDTO getById(@PathVariable Long id){
        return cityService.getById(id);
    }


}
