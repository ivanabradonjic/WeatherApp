package com.valcon.WeatherApp.controller;

import com.valcon.WeatherApp.controller.api.ThreeHourlyControllerApi;
import com.valcon.WeatherApp.service.ThreeHourlyForecastService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threeHourlyForecasts")
public class ThreeHourlyForecastController implements ThreeHourlyControllerApi {

    private final ThreeHourlyForecastService threeHourlyForecastService;

    public ThreeHourlyForecastController(ThreeHourlyForecastService threeHourlyForecastService) {
        this.threeHourlyForecastService = threeHourlyForecastService;
    }


}
