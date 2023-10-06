package com.valcon.WeatherApp.dto;


import java.io.Serializable;

public class CityResponseDTO implements Serializable {
    private String name;

    public CityResponseDTO() {
    }
    public CityResponseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
