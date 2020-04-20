package com.example.todoapplication.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.todoapplication.exception.UserNotFoundException;
import com.example.todoapplication.model.Product;
import com.example.todoapplication.model.User;
import com.example.todoapplication.orchestration.ProductServiceProxy;
import com.example.todoapplication.respository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    private MessageSource messageSource;
    private UserRepository userRepository;
    private ProductServiceProxy productServiceProxy;

    public UserController(MessageSource messageSource, UserRepository userRepository, ProductServiceProxy productServiceProxy) {
        this.messageSource = messageSource;
        this.userRepository = userRepository;
        this.productServiceProxy = productServiceProxy;
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

    @GetMapping("/hello")
    public String hello() {
        return "Hello my programmers";
    }

    @GetMapping("/products")
    @HystrixCommand(fallbackMethod = "getProductsFallback")
    public ResponseEntity<List<Product>> getProducts() {
        return productServiceProxy.getProducts();
    }

    public ResponseEntity<List<Product>> getProductsFallback() {
        ArrayList<Product> emptyProducts = new ArrayList<>();
        return new ResponseEntity<>(emptyProducts, HttpStatus.OK);
    }
}