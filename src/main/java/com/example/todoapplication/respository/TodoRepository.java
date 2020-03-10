package com.example.todoapplication.respository;

import com.example.todoapplication.model.Todo;
import com.example.todoapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByUser(User user);
}
