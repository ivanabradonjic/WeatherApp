package com.valcon.WeatherApp.repository;

import com.valcon.WeatherApp.model.ThreeHourlyForecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreeHourlyForecastRepository extends JpaRepository<ThreeHourlyForecast,Long> {
}
