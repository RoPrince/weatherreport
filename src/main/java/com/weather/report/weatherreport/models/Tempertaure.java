package com.weather.report.weatherreport.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Tempertaure {

    @JsonProperty("temp")
    private float temperature;

}
