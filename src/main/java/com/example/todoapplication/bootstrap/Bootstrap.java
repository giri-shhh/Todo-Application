package com.example.todoapplication.bootstrap;

import com.example.todoapplication.model.User;
import com.example.todoapplication.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class Bootstrap implements CommandLineRunner {

    private UserService userService;

    public Bootstrap(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        Stream.of("Girish", "Harish").map(User::new).forEach(userService::createNewUser);

    }
}
