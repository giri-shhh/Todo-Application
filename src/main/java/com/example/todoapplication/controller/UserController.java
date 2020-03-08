package com.example.todoapplication.controller;

import com.example.todoapplication.exception.UserNotFoundException;
import com.example.todoapplication.model.User;
import com.example.todoapplication.respository.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private MessageSource messageSource;
    private UserRepository userRepository;

    public UserController(MessageSource messageSource, UserRepository userRepository) {
        this.messageSource = messageSource;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> retrieveAllUsers() {
        return this.userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = this.userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent()) throw new UserNotFoundException(" - id" + id);
        return user.get();
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) throw new UserNotFoundException("id " + id);
        userRepository.deleteById(id);
    }

    @GetMapping("/greet")
    public String greet() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}