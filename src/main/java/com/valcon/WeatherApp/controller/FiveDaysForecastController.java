package com.valcon.WeatherApp.controller;


import com.valcon.WeatherApp.model.ThreeHourlyForecast;
import com.valcon.WeatherApp.service.FiveDaysForecastService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fiveDaysForecasts")
public class FiveDaysForecastController {
    private final FiveDaysForecastService fiveDaysForecastService;

    public FiveDaysForecastController(FiveDaysForecastService fiveDaysForecastService) {
        this.fiveDaysForecastService = fiveDaysForecastService;
    }


}
