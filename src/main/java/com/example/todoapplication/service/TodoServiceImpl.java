package com.example.todoapplication.service;

import com.example.todoapplication.model.User;
import com.example.todoapplication.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Override
    public List<User> getUsers() {
        return null;
    }
}
