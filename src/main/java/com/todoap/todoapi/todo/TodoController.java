package com.todoap.todoapi.todo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private TodoDAOService dao;

    public TodoController(TodoDAOService dao) {
        this.dao = dao;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return dao.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable int id) {
        return dao.getById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
        dao.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todoJson) {
        dao.updateTodo(todoJson);
        return todoJson;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createToDo(@PathVariable String username, @RequestBody Todo todoJson) {
        dao.addTodo(username, todoJson.getDescription(), todoJson.getTargetDate(), todoJson.isDone());
        return todoJson;
    }
}
