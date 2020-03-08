package com.example.todoapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private String task;

    public Todo() {
    }

    public Todo(String task) {
        this.task = task;
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", date=" + date +
                ", task='" + task + '\'' +
                '}';
    }
}
