package com.weather.lion.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.weather.lion.entity.Task;
import com.weather.lion.service.TaskService;
import com.weather.lion.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public List<Task> getAllTask(){
        return taskService.getAllTasks();
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city, Model model){

        String response = weatherService.getWeather(city);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response);
            model.addAttribute("cityName", root.path("name").asText());
            model.addAttribute("weatherDescription", root.path("weather").get(0).path("description").asText());

            double temperature = root.path("main").path("temp").asDouble() - 273.15;//La api retorna la temperatura en grados Kelvin
            String formattedTemperature = String.format("%.1f", temperature);
            model.addAttribute("temperature", formattedTemperature);
            return "weather";
        } catch (Exception e) {
            model.addAttribute("error", "Error al obtener el clima: " + e.getMessage());
            return "error";
        }
    }
}
