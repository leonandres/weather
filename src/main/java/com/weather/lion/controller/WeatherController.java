package com.weather.lion.controller;

import com.weather.lion.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public String getWeather(){
        return "";
    }
}
