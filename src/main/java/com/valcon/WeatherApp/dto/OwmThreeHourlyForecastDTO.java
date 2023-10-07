package com.valcon.WeatherApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OwmThreeHourlyForecastDTO {
    @JsonProperty("main")
    private MainInfoDTO main;
    @JsonProperty("dt_txt")
    private String dateTime;

    public MainInfoDTO getMain() {
        return main;
    }

    public String getDateTime() {
        return dateTime;
    }
}
