package com.todo.backend.api;

import com.todo.backend.api.dto.todoapi.CreateTodoRequest;
import com.todo.backend.api.dto.todoapi.CreateTodoResponse;
import com.todo.backend.api.dto.todoapi.DeleteTodoRequest;
import com.todo.backend.api.dto.todoapi.ReadTodoRequest;
import com.todo.backend.api.dto.todoapi.ReadTodoResponse;
import com.todo.backend.api.dto.todoapi.TodosResponse;
import com.todo.backend.api.dto.todoapi.UpdateTodoRequest;
import com.todo.backend.api.dto.todoapi.UpdateTodoResponse;
import com.todo.backend.model.Todo;
import com.todo.backend.model.User;
import com.todo.backend.model.enumeration.Status;
import com.todo.backend.model.id.TodoId;
import com.todo.backend.model.id.UserId;
import com.todo.backend.repository.TodoRepository;
import com.todo.backend.repository.UserRepository;
import com.todo.backend.repository.tuple.TodoTodosTuple;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TodoApi {
    private static final Logger LOG = LoggerFactory.getLogger(TodoApi.class);

    @Inject private TodoRepository todoRepository;

    @Inject private UserRepository userRepository;

    public ReadTodoResponse readTodo(ReadTodoRequest dto) {
        LOG.trace("readTodo {}", dto.getId());

        final Todo model = todoRepository.getOne(dto.getId());
        final TodoId responseId = model.getId();
        final UserId responseUserId = model.getUserId();
        final String responseTask = model.getTask();
        final ZonedDateTime responseDate = model.getDate();
        final Status responseStatus = model.getStatus();
        return new ReadTodoResponse(responseId, responseUserId, responseTask, responseDate, responseStatus);
    }

    public CreateTodoResponse createTodo(CreateTodoRequest dto) {
        LOG.trace("createTodo {}", dto.getUserId());

        final User user = userRepository.getOne(dto.getUserId());
        final String task = dto.getTask();
        final ZonedDateTime date = dto.getDate();
        final Status status = dto.getStatus();
        final Todo model = todoRepository.create(null, null, null, null);

        final TodoId responseId = model.getId();
        final UserId responseUserId = model.getUserId();
        final String responseTask = model.getTask();
        final ZonedDateTime responseDate = model.getDate();
        final Status responseStatus = model.getStatus();
        return new CreateTodoResponse(responseId, responseUserId, responseTask, responseDate, responseStatus);
    }

    public UpdateTodoResponse updateTodo(UpdateTodoRequest dto) {
        LOG.trace("updateTodo {} {}", dto.getId(), dto.getUserId());

        final Todo model = todoRepository.getOne(dto.getId());
        model.setUser(userRepository.getOne(dto.getUserId()));
        model.setTask(dto.getTask());
        model.setDate(dto.getDate());
        model.setStatus(dto.getStatus());
        todoRepository.update(model);

        final TodoId responseId = model.getId();
        final UserId responseUserId = model.getUserId();
        final String responseTask = model.getTask();
        final ZonedDateTime responseDate = model.getDate();
        final Status responseStatus = model.getStatus();
        return new UpdateTodoResponse(responseId, responseUserId, responseTask, responseDate, responseStatus);
    }

    public void deleteTodo(DeleteTodoRequest dto) {
        LOG.trace("deleteTodo {}", dto.getId());

        todoRepository.deleteById(dto.getId());
    }

    public List<TodosResponse> todos() {
        LOG.trace("todos");
        // TODO check security constraints

        final List<TodoTodosTuple> tuples = todoRepository.todos();
        return tuples.stream()
                .map(
                        tuple -> {
                            final TodoId responseId = tuple.getTodo().getId();
                            final String responseUserUsername = tuple.getUser().getUsername();
                            final String responseTask = tuple.getTodo().getTask();
                            final ZonedDateTime responseDate = tuple.getTodo().getDate();
                            final Status responseStatus = tuple.getTodo().getStatus();
                            return new TodosResponse(responseId, responseUserUsername, responseTask, responseDate, responseStatus);
                        })
                .collect(Collectors.toList());
    }
}
