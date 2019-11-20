package com.weather.report.weatherreport.provider;

import com.weather.report.weatherreport.configurations.AppConfig;
import com.weather.report.weatherreport.models.CityForecastModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class WeatherForecastProvider {

    @Autowired
    RestTemplate restTemplate;

    final
    AppConfig appConfig;

    @Autowired
    public WeatherForecastProvider(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public CityForecastModel getWeatherForecastForCity() {

        ResponseEntity<CityForecastModel> response = null;

        try {
            response = restTemplate.getForEntity(appConfig.getWeatherUrl(), CityForecastModel.class);

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return response.getBody();

    }
}
