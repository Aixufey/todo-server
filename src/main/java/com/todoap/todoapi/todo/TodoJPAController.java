package com.todoap.todoapi.todo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DAO Service for database utilizing JPA Repository
 */
@RestController
public class TodoJPAController {
    private TodoRepository dbRepo;

    public TodoJPAController(TodoRepository dbRepo) {
        this.dbRepo = dbRepo;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return dbRepo.getByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable int id) {
        return dbRepo.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> delete(@PathVariable String username, @PathVariable int id){
        dbRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> update(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo){
        Todo todoUpdated = dbRepo.save(todo);
        return ResponseEntity.ok(todoUpdated);
    }

    @PostMapping("/users/{username}/todos")
    public Todo create(@PathVariable String username, @RequestBody Todo todoJson){
        todoJson.setId(null);
        todoJson.setUsername(username);
        return dbRepo.save(todoJson);

//        Todo todo = dbRepo.create(username, todoJson.getDescription(), todoJson.getTargetDate(), todoJson.isDone());
//        return todo;
    }
}
