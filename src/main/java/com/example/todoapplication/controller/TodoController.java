package com.example.todoapplication.controller;

import com.example.todoapplication.exception.UserNotFoundException;
import com.example.todoapplication.model.Product;
import com.example.todoapplication.model.Todo;
import com.example.todoapplication.respository.TodoRepository;
import com.example.todoapplication.respository.UserRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("todos/{userId}")
public class TodoController {

    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public TodoController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Todo> retrieveAllTodo(@PathVariable Long userId) {
        return this.todoRepository.findAllByUser(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("id " + userId)));
    }
}
