package com.valcon.WeatherApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String countryCode;

    @OneToOne(mappedBy = "city")
    private FiveDaysForecast fiveDaysForecast;

    public City() {
    }

    public City(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public FiveDaysForecast getFiveDaysForecast() {
        return fiveDaysForecast;
    }

    public void setFiveDaysForecast(FiveDaysForecast fiveDaysForecast) {
        this.fiveDaysForecast = fiveDaysForecast;
    }
}
