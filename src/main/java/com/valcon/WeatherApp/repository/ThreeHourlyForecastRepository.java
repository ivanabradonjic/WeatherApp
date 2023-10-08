package com.valcon.WeatherApp.repository;

import com.valcon.WeatherApp.model.FiveDaysForecast;
import com.valcon.WeatherApp.model.ThreeHourlyForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ThreeHourlyForecastRepository extends JpaRepository<ThreeHourlyForecast,Long> {
    @Query("SELECT thf.temp FROM ThreeHourlyForecast thf WHERE thf.fiveDaysForecast = :fdf AND (thf.dateTime BETWEEN :startDateTime AND :endDateTime) ")
    List<Double> findByDataTimeBetween(
            @Param("fdf") FiveDaysForecast fiveDaysForecast,
             LocalDateTime startDateTime,
             LocalDateTime endDateTime
    );

    List<ThreeHourlyForecast> findByFiveDaysForecast(FiveDaysForecast fiveDaysForecast);
}

