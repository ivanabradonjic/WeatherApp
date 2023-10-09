package com.valcon.WeatherApp.controller;


import com.valcon.WeatherApp.controller.api.FiveDaysForecastControllerApi;
import com.valcon.WeatherApp.dto.CityAvgTempResponseDTO;
import com.valcon.WeatherApp.dto.TimeIntervalParametersDTO;
import com.valcon.WeatherApp.service.FiveDaysForecastService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fiveDaysForecasts")
public class FiveDaysForecastController implements FiveDaysForecastControllerApi {
    private final FiveDaysForecastService fiveDaysForecastService;

    public FiveDaysForecastController(FiveDaysForecastService fiveDaysForecastService) {
        this.fiveDaysForecastService = fiveDaysForecastService;
    }

    @GetMapping("/{name}")
    public CityAvgTempResponseDTO averageTemperatureByCity(@PathVariable String name ,@RequestBody TimeIntervalParametersDTO timeIntervalParametersDTO){
        return fiveDaysForecastService.averageTemperatureByCity(name, timeIntervalParametersDTO);
    }
    @GetMapping
    public List<CityAvgTempResponseDTO> allCitiesAverageTemperatures(@RequestBody TimeIntervalParametersDTO timeIntervalParametersDTO){
        return fiveDaysForecastService.allCityAverageTemperatures(timeIntervalParametersDTO);
    }

}
