package com.example.todoapplication.service;

import com.example.todoapplication.model.User;

import java.util.List;

public interface UserService {

    Long createNewUser(User user);

    List<User> getUsers();

    User getUser(Long id) throws Exception;
}
