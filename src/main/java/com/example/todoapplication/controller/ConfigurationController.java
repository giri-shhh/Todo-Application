package com.example.todoapplication.controller;

import com.example.todoapplication.model.Configuration;
import com.example.todoapplication.model.TodoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {

    private Configuration configuration;

    public ConfigurationController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("todo-config")
    public TodoConfiguration retrieveTodoConfiguration() {
        return new TodoConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }
}
