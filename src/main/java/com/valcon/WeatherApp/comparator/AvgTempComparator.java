package com.valcon.WeatherApp.comparator;

import com.valcon.WeatherApp.dto.CityAvgTempResponseDTO;

import java.util.Comparator;

public class AvgTempComparator implements Comparator<CityAvgTempResponseDTO> {
    @Override
    public int compare(CityAvgTempResponseDTO o1, CityAvgTempResponseDTO o2) {
        return Double.compare(o1.getCityAvgTemp(), o2.getCityAvgTemp());
    }
}
