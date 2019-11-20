package com.weather.report.weatherreport.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CityForecastModel {

    @JsonProperty("list")
    private ArrayList<DailyForecast> list;
}
