package com.weather.lion.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String API_KEY = "594c1f525b647dcb69919fb9a4c7206c";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public String getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "?q=" + city + "&appid=" + API_KEY;
        return restTemplate.getForObject(url, String.class);
    }
}
