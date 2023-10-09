package com.valcon.WeatherApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainInfoDTO {
    @JsonProperty("temp")
    private double temp;


    public MainInfoDTO() {
    }

    public double getTemp() {
        return temp;
    }

    public MainInfoDTO(double temp) {
        this.temp = temp;
    }

}
