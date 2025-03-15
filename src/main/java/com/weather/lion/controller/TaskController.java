package com.weather.lion.controller;

import com.weather.lion.entity.Task;
import com.weather.lion.service.TaskService;
import com.weather.lion.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
    public String getWeather(@RequestParam String city){
        return weatherService.getWeather(city);
    }
}
