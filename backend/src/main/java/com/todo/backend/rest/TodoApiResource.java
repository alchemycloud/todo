package com.todo.backend.rest;

import com.todo.backend.api.TodoApiCaller;
import com.todo.backend.api.dto.todoapi.CreateTodoRequest;
import com.todo.backend.api.dto.todoapi.CreateTodoResponse;
import com.todo.backend.api.dto.todoapi.DeleteTodoRequest;
import com.todo.backend.api.dto.todoapi.ReadTodoRequest;
import com.todo.backend.api.dto.todoapi.ReadTodoResponse;
import com.todo.backend.api.dto.todoapi.TodosResponse;
import com.todo.backend.api.dto.todoapi.UpdateTodoRequest;
import com.todo.backend.api.dto.todoapi.UpdateTodoResponse;
import com.todo.backend.model.id.TodoId;
import com.todo.backend.rest.dto.todoapi.RestUpdateTodoRequest;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class TodoApiResource {
    private static final Logger LOG = LoggerFactory.getLogger(TodoApiResource.class);

    @Inject private TodoApiCaller todoApiCaller;

    @GetMapping(value = "/todo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReadTodoResponse> readTodo(@PathVariable Long id) {
        LOG.debug("GET /todo/{}", id);

        final ReadTodoRequest request = new ReadTodoRequest(new TodoId(id));
        final ReadTodoResponse response = todoApiCaller.readTodo(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateTodoResponse> createTodo(@Valid @RequestBody CreateTodoRequest request) {
        LOG.debug("POST /todo {}", request);

        final CreateTodoResponse response = todoApiCaller.createTodo(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/todo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateTodoResponse> updateTodo(@PathVariable Long id, @Valid @RequestBody RestUpdateTodoRequest restRequest) {
        LOG.trace("PUT /todo/{} {}", id, restRequest);

        final UpdateTodoRequest request =
                new UpdateTodoRequest(new TodoId(id), restRequest.getUserId(), restRequest.getTask(), restRequest.getDate(), restRequest.getStatus());
        final UpdateTodoResponse response = todoApiCaller.updateTodo(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/todo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        LOG.debug("DELETE /todo/{}", id);

        final DeleteTodoRequest request = new DeleteTodoRequest(new TodoId(id));
        todoApiCaller.deleteTodo(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MEMBER')")
    public ResponseEntity<List<TodosResponse>> todos() {
        LOG.debug("GET /todos");

        final List<TodosResponse> response = todoApiCaller.todos();
        return ResponseEntity.ok().body(response);
    }
}
