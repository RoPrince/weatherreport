package com.weather.report.weatherreport.provider;

import com.weather.report.weatherreport.configurations.AppConfig;
import com.weather.report.weatherreport.models.CityForecastModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Repository
public class WeatherForecastProvider {

    @Autowired
    RestTemplate restTemplate;

    final
    AppConfig appConfig;

    private Logger logger = LoggerFactory.getLogger(WeatherForecastProvider.class);

    @Autowired
    public WeatherForecastProvider(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public CityForecastModel getWeatherForecastForCity(String location) {

        ResponseEntity<CityForecastModel> response;
        String url = String.format(appConfig.getWeatherUrl(), location);

        try {
            response = restTemplate.getForEntity(url, CityForecastModel.class);
            if (response.getStatusCode().is2xxSuccessful())
                return response.getBody();

        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(e.getStatusCode());
        } catch (Exception e) {
            logger.error("Unexpected Error: " + e.getStackTrace());
            throw e;
        }
        return null;
    }
}
