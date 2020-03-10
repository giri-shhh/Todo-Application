package com.example.todoapplication.bootstrap;

import com.example.todoapplication.model.User;
import com.example.todoapplication.respository.TodoRepository;
import com.example.todoapplication.respository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Bootstrap implements CommandLineRunner {

    private UserRepository userRepository;

    private TodoRepository todoRepository;

    public Bootstrap(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... args) {

        userRepository.saveAll(Stream.of("Girish", "Harish").map(User::new).collect(Collectors.toList()));

    }
}
