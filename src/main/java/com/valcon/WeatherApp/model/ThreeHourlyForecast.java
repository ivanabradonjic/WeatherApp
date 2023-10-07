package com.valcon.WeatherApp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ThreeHourlyForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double temp;
    LocalDateTime dateTime;

    @ManyToOne()
    @JoinColumn(name = "fiveDaysForecast_id", nullable = false)
    private FiveDaysForecast fiveDaysForecast;

    public ThreeHourlyForecast() {
    }

    public ThreeHourlyForecast(double temp, LocalDateTime dateTime, FiveDaysForecast fiveDaysForecast) {
        this.temp = temp;
        this.dateTime = dateTime;
        this.fiveDaysForecast = fiveDaysForecast;
    }

    public ThreeHourlyForecast(double temp, LocalDateTime dateTime) {
        this.temp = temp;
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public FiveDaysForecast getFiveDaysForecast() {
        return fiveDaysForecast;
    }

    public void setFiveDaysForecast(FiveDaysForecast fiveDaysForecast) {
        this.fiveDaysForecast = fiveDaysForecast;
    }
}
