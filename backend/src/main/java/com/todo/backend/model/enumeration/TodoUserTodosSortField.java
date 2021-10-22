package com.todo.backend.model.enumeration;

public enum TodoUserTodosSortField {
    TODO_TASK("todo.task");

    private final String name;

    TodoUserTodosSortField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
