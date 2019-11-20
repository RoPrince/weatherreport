package com.weather.report.weatherreport.controller;

import com.weather.report.weatherreport.models.WeatherResponse;
import com.weather.report.weatherreport.service.RainPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherPredictionController {

    @Autowired
    private RainPredictionService rainPredictionService;

    @GetMapping("/getWeather/{location}")
    public ResponseEntity<List<WeatherResponse>> getWeatherForCity(@PathVariable String location) {
        List<WeatherResponse> response = rainPredictionService.getWeatherForCity(location);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
