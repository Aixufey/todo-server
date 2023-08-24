package com.todoap.todoapi.todo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@Component
public class TodoDAOService {

    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "test", "Learn Full stack", LocalDate.now().plusYears(10), false));
        todos.add(new Todo(++todosCount, "test", "Learn .NET", LocalDate.now().plusYears(20), false));
        todos.add(new Todo(++todosCount, "test", "Learn iOS", LocalDate.now().plusYears(30), false));
    }

    public List<Todo> getAll() {
        return todos;
    }
    public Todo getById(int id) {
        Predicate<? super Todo> pred = todo -> todo.getId().equals(id);
        return todos.stream().filter(pred).findFirst().orElse(null);
    }
    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> pred = todo -> todo.getUsername().equals(username);
        return todos.stream().filter(pred).toList();
    }

    public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
        return todo;
    }

    public void deleteById(int id) {
        Predicate<? super Todo> pred = todo -> todo.getId().equals(id);
        todos.removeIf(pred);
    }
}
