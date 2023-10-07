package com.valcon.WeatherApp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FiveDaysForecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "fiveDaysForecast")
    private List<ThreeHourlyForecast> threeHourlyForecasts = new ArrayList<>();

    public FiveDaysForecast() {
    }

    public FiveDaysForecast(City city) {
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<ThreeHourlyForecast> getThreeHourlyForecasts() {
        return threeHourlyForecasts;
    }


    public void setThreeHourlyForecasts(List<ThreeHourlyForecast> threeHourlyForecasts) {
        this.threeHourlyForecasts = threeHourlyForecasts;
    }
}
