package com.example.todoapplication.respository;

import com.example.todoapplication.model.User;

import java.util.List;

public interface UserDao {
    Long createNewUser(User user);

    List<User> getUsers();

    User getUser(Long id) throws Exception;
}
