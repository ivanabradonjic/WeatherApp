package com.valcon.WeatherApp.dto;


import java.io.Serializable;

public class CityResponseDTO implements Serializable {
    private Long id;
    private String name;

    public CityResponseDTO() {
    }

    public CityResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
