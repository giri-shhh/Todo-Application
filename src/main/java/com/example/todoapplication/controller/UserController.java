package com.example.todoapplication.controller;

import com.example.todoapplication.exception.UserNotFoundException;
import com.example.todoapplication.model.User;
import com.example.todoapplication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> retrieveAllUsers() {
        return this.userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        Long userId = this.userService.createNewUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) throws Exception {
        User user = this.userService.getUser(id);
        if (user == null) throw new UserNotFoundException(" - id" + id);

        return user;
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Long id) {
        List<User> users = userService.getUsers();
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (!removed) throw new UserNotFoundException("id " + id);
    }

}