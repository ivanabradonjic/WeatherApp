package com.valcon.WeatherApp.repository;

import com.valcon.WeatherApp.model.City;
import com.valcon.WeatherApp.model.FiveDaysForecast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiveDaysForecastRepository extends JpaRepository<FiveDaysForecast,Long> {
    FiveDaysForecast findByCity(City city);
}
