package com.valcon.WeatherApp.service.impl;

import com.valcon.WeatherApp.dto.OwmThreeHourlyForecastDTO;
import com.valcon.WeatherApp.exception.InvalidIntervalParametersException;
import com.valcon.WeatherApp.mapper.ThreeHourlyForecastMapper;
import com.valcon.WeatherApp.model.FiveDaysForecast;
import com.valcon.WeatherApp.model.ThreeHourlyForecast;
import com.valcon.WeatherApp.repository.ThreeHourlyForecastRepository;
import com.valcon.WeatherApp.service.ThreeHourlyForecastService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ThreeHourlyForecastServiceImpl implements ThreeHourlyForecastService {
    public static final double KELVIN_CELSIUS_TEMP_DIFFERENCE = 273.15;
    private final ThreeHourlyForecastRepository threeHourlyForecastRepository;

    public ThreeHourlyForecastServiceImpl(ThreeHourlyForecastRepository threeHourlyForecastRepository) {
        this.threeHourlyForecastRepository = threeHourlyForecastRepository;
    }

    public void saveOne(OwmThreeHourlyForecastDTO owmThreeHourlyForecastDTO, FiveDaysForecast fiveDaysForecast) {

        ThreeHourlyForecast threeHourlyForecast = ThreeHourlyForecastMapper.mapToEntity(owmThreeHourlyForecastDTO);
        threeHourlyForecast.setFiveDaysForecast(fiveDaysForecast);

        threeHourlyForecastRepository.save(threeHourlyForecast);

    }

    private LocalDateTime getFirstDateTime(FiveDaysForecast fiveDaysForecast) {

        List<ThreeHourlyForecast> foundedThreeHourlyForecast = threeHourlyForecastRepository.findByFiveDaysForecast(fiveDaysForecast);

        return foundedThreeHourlyForecast.get(0).getDateTime();
    }

    public int averageTemperatureInInterval(FiveDaysForecast fiveDaysForecast, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        checkIntervalParameters(fiveDaysForecast, startDateTime, endDateTime);
        List<Double> foundedTemps= new ArrayList<>();

        if(startDateTime.isBefore(getFirstDateTime(fiveDaysForecast))){
            foundedTemps = threeHourlyForecastRepository.findByDataTimeBetween(fiveDaysForecast, startDateTime, endDateTime);
        }else {
            foundedTemps = threeHourlyForecastRepository.findByDataTimeBetween(fiveDaysForecast, startDateTime.minusMinutes(169), endDateTime);

        }

        int numberOfTemp = 0;
        double sumOfTemp = 0.0;
        for (Double temp : foundedTemps) {
            sumOfTemp += temp;
            numberOfTemp++;
        }
        double avgKelvinTemp = sumOfTemp / numberOfTemp;
        double avgCelsiusTemp = avgKelvinTemp - KELVIN_CELSIUS_TEMP_DIFFERENCE;
        return Math.round((float) avgCelsiusTemp);
    }

    private void checkIntervalParameters(FiveDaysForecast fiveDaysForecast, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        LocalDateTime firstStartDateTime = getFirstDateTime(fiveDaysForecast);

        if (startDateTime.isBefore(LocalDateTime.now()) || endDateTime.isBefore(LocalDateTime.now())) {
            throw new InvalidIntervalParametersException("Interval parameters can't be in past.");
        }
        if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
            throw new InvalidIntervalParametersException("Start date has to be before end date.");

        }
        if (endDateTime.isAfter(firstStartDateTime.plusHours(117))) {
            throw new InvalidIntervalParametersException("End date must be within 5 days from start date");
        }


    }

}
