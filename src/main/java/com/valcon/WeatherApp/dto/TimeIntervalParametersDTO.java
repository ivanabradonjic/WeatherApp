package com.valcon.WeatherApp.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TimeIntervalParametersDTO implements Serializable {
    @Future(message = "Interval start time can't be in the past")
    @NotNull(message = "Interval start time is mandatory")
    private LocalDateTime startDateTime;

    @Future(message = "Interval end time can't be in the past")
    @NotNull(message = "Interval end time is mandatory")
    private LocalDateTime endDateTime;

    public TimeIntervalParametersDTO() {
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public TimeIntervalParametersDTO(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
