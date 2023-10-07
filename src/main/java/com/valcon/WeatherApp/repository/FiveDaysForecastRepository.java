package com.valcon.WeatherApp.repository;

import com.valcon.WeatherApp.model.FiveDaysForecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiveDaysForecastRepository extends JpaRepository<FiveDaysForecast,Long> {
}
