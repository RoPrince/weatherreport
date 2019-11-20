package com.weather.report.weatherreport.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class WeatherForecast {

    @JsonProperty("weather_forecast")
    private ArrayList<WeatherResponse> weatherResponses;
}
