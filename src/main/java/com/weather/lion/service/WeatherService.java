package com.weather.lion.service;

import org.springframework.stereotype.Service;
@Service
public class WeatherService {

    private static final String API_KEY = "594c1f525b647dcb69919fb9a4c7206c";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public String getWeather(String city) {
        return "";
    }
}
