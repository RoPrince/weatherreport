package com.weather.report.weatherreport.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class DailyForecast {

    @JsonProperty("dt_txt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate date;

    @JsonProperty("main")
    private Tempertaure main;

    @JsonProperty("weather")
    private ArrayList<Weather> weather;
}
