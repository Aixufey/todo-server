package com.todoap.todoapi.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer>{


    List<Todo> getByUsername(String username);
}
