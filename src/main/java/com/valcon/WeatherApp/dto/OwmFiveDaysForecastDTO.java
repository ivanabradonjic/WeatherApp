package com.valcon.WeatherApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OwmFiveDaysForecastDTO {
    @JsonProperty("list")
    private List<OwmThreeHourlyForecastDTO> owmThreeHourlyForecastDTOList;

    public OwmFiveDaysForecastDTO() {
    }

    public void setOwmThreeHourlyForecastDTOList(List<OwmThreeHourlyForecastDTO> owmThreeHourlyForecastDTOList) {
        this.owmThreeHourlyForecastDTOList = owmThreeHourlyForecastDTOList;
    }

    public List<OwmThreeHourlyForecastDTO> getOwmThreeHourlyForecastDTOList() {
        return owmThreeHourlyForecastDTOList;
    }
}
