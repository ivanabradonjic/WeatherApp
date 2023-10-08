package com.valcon.WeatherApp.dto;

public class CityAvgTempResponseDTO {
    private String cityName;
    private double cityAvgTemp;

    public CityAvgTempResponseDTO(String cityName, double cityAvgTemp) {
        this.cityName = cityName;
        this.cityAvgTemp = cityAvgTemp;
    }

    public String getCityName() {
        return cityName;
    }

    public double getCityAvgTemp() {
        return cityAvgTemp;
    }
}
