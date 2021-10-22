package com.todo.backend.repository;

import com.todo.backend.model.Todo;
import com.todo.backend.model.User;
import com.todo.backend.model.enumeration.Status;
import com.todo.backend.model.enumeration.TodoUserTodosSortField;
import com.todo.backend.model.id.TodoId;
import com.todo.backend.model.id.UserId;
import com.todo.backend.repository.tuple.TodoTodosTuple;
import com.todo.backend.repository.util.OrderableField;
import java.time.ZonedDateTime;
import java.util.List;

public interface TodoRepository extends SimpleRepository<Todo, TodoId> {

    Todo create(User user, String task, ZonedDateTime date, Status status);

    List<Todo> findByTask(String task);

    List<Todo> findByDate(ZonedDateTime date);

    List<Todo> findByStatus(Status status);

    List<Todo> userTodos(UserId userId, List<OrderableField<TodoUserTodosSortField>> orderableFields);

    List<TodoTodosTuple> todos();
}
