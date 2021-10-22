package com.todo.backend.repository.tuple;

import com.todo.backend.model.Todo;
import com.todo.backend.model.User;

public class TodoTodosTuple {

    private final Todo todo;
    private final User user;

    public TodoTodosTuple(Todo todo, User user) {
        this.todo = todo;
        this.user = user;
    }

    public Todo getTodo() {
        return todo;
    }

    public User getUser() {
        return user;
    }
}
