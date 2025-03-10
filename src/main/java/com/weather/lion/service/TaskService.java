package com.weather.lion.service;

import com.weather.lion.entity.Task;
import com.weather.lion.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaskService {
    @Autowired
    private ITaskRepository iTaskRepository;

    public List<Task> getAllTasks() {
        return iTaskRepository.findAll();
    }
}
