package com.weather.lion.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.lion.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public String getWeather(@RequestParam String city, Model model){

        String response = weatherService.getWeather(city);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response);

            if (root.path("cod").asInt() != 200) {
                model.addAttribute("error", "Ciudad no encontrada: " + city);
                return "error";
            }

            model.addAttribute("cityName", root.path("name").asText());
            model.addAttribute("weatherDescription", root.path("weather").get(0).path("description").asText());

            double celsiusTemperature = root.path("main").path("temp").asDouble() - 273.15;
            String formattedTemperature = String.format("%.1f",celsiusTemperature);
            model.addAttribute("temperature", formattedTemperature);

            model.addAttribute("humidity", root.path("main").path("humidity").asText() + "%");
            model.addAttribute("windSpeed", root.path("wind").path("speed").asText() + " km/h");

            String description = root.path("weather").get(0).path("description").asText();

            String weatherIcon = determineWeatherIcon(description);
            String weatherClass = determineWeatherClass(description);

            model.addAttribute("weatherIcon", weatherIcon);
            model.addAttribute("weatherClass", weatherClass);
            return "weather";
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener el clima:" + e.getMessage());
            return "error";
        }
    }

    private String determineWeatherClass(String description) {
        String lowerDesc = description.toLowerCase();
        if (lowerDesc.contains("sol")) return "sunny";
        if (lowerDesc.contains("lluvia")) return "rainy";
        return "cloudy";
    }

    private String determineWeatherIcon(String description) {
        String lowerDesc = description.toLowerCase();
        if (lowerDesc.contains("sol")) return "fa-sun";
        if (lowerDesc.contains("lluvia")) return "fa-cloud-rain";
        return "fa-cloud";
    }
}
