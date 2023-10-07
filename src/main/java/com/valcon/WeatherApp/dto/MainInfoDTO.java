package com.valcon.WeatherApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainInfoDTO {
    @JsonProperty("temp")
    private double temp;

    public double getTemp() {
        return temp;
    }
}
