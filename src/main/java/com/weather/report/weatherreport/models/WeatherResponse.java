package com.weather.report.weatherreport.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WeatherResponse {

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("temp")
    private float temperature;

    @JsonProperty("weather")
    private String weather;

    @JsonProperty("usertip")
    private String user_tip;
}
