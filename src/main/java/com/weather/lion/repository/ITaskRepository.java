package com.weather.lion.repository;

import com.weather.lion.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Long> {
}
