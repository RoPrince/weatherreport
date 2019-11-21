package com.weather.report.weatherreport.service;

import com.weather.report.weatherreport.models.CityForecastModel;
import com.weather.report.weatherreport.models.DailyForecast;
import com.weather.report.weatherreport.models.WeatherResponse;
import com.weather.report.weatherreport.provider.WeatherForecastProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RainPredictionService {

    @Autowired
    private WeatherForecastProvider weatherForecastProvider;

    public List<WeatherResponse> getWeatherForCity(String location) {

        CityForecastModel cityForecastModel = weatherForecastProvider.getWeatherForecastForCity(location);
        List<WeatherResponse> weatherResponses = null;
        if (!ObjectUtils.isEmpty(cityForecastModel)) {
            weatherResponses = getWeatherFor(3, cityForecastModel);
        }
        return weatherResponses;
    }

    private List<WeatherResponse> getWeatherFor(int days, CityForecastModel cityForecastModel) {

        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(days);

        List<DailyForecast> dailyForecastList = cityForecastModel.getList().
                stream().
                filter(dailyForecast -> (!dailyForecast.getDate().isBefore(today)) && (!dailyForecast.getDate().isAfter(endDate))).collect(Collectors.toList());

        List<DailyForecast> dailyForecastListTemp = dailyForecastList.
                stream().
                filter(i -> i.getMain().getTemperature() > 280.00).
                collect(Collectors.toList());

        List<DailyForecast> dailyForecastListWeather =
                dailyForecastList.stream().filter(dailyForecast -> dailyForecast.getWeather().get(0).getMain().equals("Rain")).collect(Collectors.toList());

        List<DailyForecast> dailyForecasts = new ArrayList<>();
        dailyForecasts.addAll(dailyForecastListTemp);
        dailyForecasts.addAll(dailyForecastListWeather);

        List<DailyForecast> dailyForecastsFiltered = dailyForecasts.stream().filter(distinctByKey(dailyForecast -> dailyForecast.getDate())).collect(Collectors.toList());

        List<WeatherResponse> weatherResponses = new ArrayList<>();

        for (DailyForecast forcast :
                dailyForecastsFiltered) {
            String userTip = (forcast.getWeather().get(0).getMain().equals("Rain")) ? "Carry umberlla" : "Carry Sunscreen";
            WeatherResponse weatherResponse = new WeatherResponse();
            weatherResponse.setDate(forcast.getDate());
            weatherResponse.setTemperature(forcast.getMain().getTemperature());
            weatherResponse.setWeather(forcast.getWeather().get(0).getMain());
            weatherResponse.setUser_tip(userTip);
            weatherResponses.add(weatherResponse);
        }

        //   List<WeatherResponse> weatherResponsesFiltered = weatherResponses.stream().filter(distinctByKey(weatherResponse -> weatherResponse.getDate())).collect(Collectors.toList());

        return weatherResponses;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
