package com.example.todoapplication.respository;

import com.example.todoapplication.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserDaoImpl implements UserDao {

    private AtomicLong atomicLong = new AtomicLong();
    List<User> users = new ArrayList<>();

    @Override
    public Long createNewUser(User user) {
        user.setId(atomicLong.incrementAndGet());
        user.setDateOfBirth(LocalDate.now());
        users.add(user);
        return user.getId();
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUser(Long id) throws Exception {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(() -> new Exception("User does not exists"));
    }
}
