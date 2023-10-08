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
    private final ThreeHourlyForecastRepository threeHourlyForecastRepository;

    public ThreeHourlyForecastServiceImpl(ThreeHourlyForecastRepository threeHourlyForecastRepository) {
        this.threeHourlyForecastRepository = threeHourlyForecastRepository;
    }

    public void saveOne(OwmThreeHourlyForecastDTO owmThreeHourlyForecastDTO, FiveDaysForecast fiveDaysForecast) {

        /*double temp = owmThreeHourlyForecastDTO.getMain().getTemp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(owmThreeHourlyForecastDTO.getDateTime(), formatter);
        ThreeHourlyForecast threeHourlyForecast = new ThreeHourlyForecast(temp,dateTime,fiveDaysForecast);*/
        //       fiveDaysForecast.getThreeHourlyForecasts().add(threeHourlyForecast);
        ThreeHourlyForecast threeHourlyForecast = ThreeHourlyForecastMapper.mapToEntity(owmThreeHourlyForecastDTO);
        threeHourlyForecast.setFiveDaysForecast(fiveDaysForecast);

        threeHourlyForecastRepository.save(threeHourlyForecast);

    }

    public LocalDateTime getFirstDateTime(FiveDaysForecast fiveDaysForecast){

        List<ThreeHourlyForecast> foundedThreeHourlyForecast = threeHourlyForecastRepository.findByFiveDaysForecast(fiveDaysForecast);

        return foundedThreeHourlyForecast.get(0).getDateTime();
    }

    public double averageTemperatureInInterval(FiveDaysForecast fiveDaysForecast, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        List<Double> foundedTemp;
        checkIntervalParameters(fiveDaysForecast, startDateTime,endDateTime);

        foundedTemp = threeHourlyForecastRepository.findByDataTimeBetween(fiveDaysForecast, startDateTime.minusMinutes(169), endDateTime);

        int numberOfTemperature = 0;
        double sumOfTemperature = 0.0;
        for (Double temp : foundedTemp) {
            sumOfTemperature += temp;
            numberOfTemperature++;
        }
        return sumOfTemperature / numberOfTemperature;
    }

    public void checkIntervalParameters(FiveDaysForecast fiveDaysForecast, LocalDateTime startDateTime, LocalDateTime endDateTime){

        LocalDateTime firstStartDateTime = getFirstDateTime(fiveDaysForecast);

        if(startDateTime.isBefore(LocalDateTime.now() )|| endDateTime.isBefore(LocalDateTime.now())){
            throw new InvalidIntervalParametersException("Interval parameters can't be in past.");
        }
        if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
            throw new InvalidIntervalParametersException("Start date has to be before end date.");

        }
        if (endDateTime.isAfter(firstStartDateTime.plusHours(117))) {
            throw new InvalidIntervalParametersException("End date must be within 5 days from start date");
        }

        if(startDateTime.isBefore(firstStartDateTime)){
            throw new InvalidIntervalParametersException("Start time must be after "+ firstStartDateTime );
        }
    }

}
