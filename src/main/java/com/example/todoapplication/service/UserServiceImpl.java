package com.example.todoapplication.service;

import com.example.todoapplication.model.User;
import com.example.todoapplication.respository.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Long createNewUser(User user) {
        return userDao.createNewUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUser(Long id) throws Exception {
        return userDao.getUser(id);
    }

}
