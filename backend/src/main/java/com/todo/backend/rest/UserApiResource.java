package com.todo.backend.rest;

import com.querydsl.core.types.Order;
import com.todo.backend.api.UserApiCaller;
import com.todo.backend.api.dto.userapi.CreateUserRequest;
import com.todo.backend.api.dto.userapi.CreateUserResponse;
import com.todo.backend.api.dto.userapi.DeleteUserRequest;
import com.todo.backend.api.dto.userapi.ReadUserRequest;
import com.todo.backend.api.dto.userapi.ReadUserResponse;
import com.todo.backend.api.dto.userapi.UpdateUserRequest;
import com.todo.backend.api.dto.userapi.UpdateUserResponse;
import com.todo.backend.api.dto.userapi.UserTodosRequest;
import com.todo.backend.api.dto.userapi.UserTodosResponse;
import com.todo.backend.api.dto.userapi.UsersResponse;
import com.todo.backend.model.enumeration.TodoUserTodosSortField;
import com.todo.backend.model.id.UserId;
import com.todo.backend.rest.dto.userapi.RestUpdateUserRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class UserApiResource {
    private static final Logger LOG = LoggerFactory.getLogger(UserApiResource.class);

    @Inject private UserApiCaller userApiCaller;

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ReadUserResponse> readUser(@PathVariable Long id) {
        LOG.debug("GET /user/{}", id);

        final ReadUserRequest request = new ReadUserRequest(new UserId(id));
        final ReadUserResponse response = userApiCaller.readUser(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        LOG.debug("POST /user {}", request);

        final CreateUserResponse response = userApiCaller.createUser(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody RestUpdateUserRequest restRequest) {
        LOG.trace("PUT /user/{} {}", id, restRequest);

        final UpdateUserRequest request =
                new UpdateUserRequest(
                        new UserId(id),
                        restRequest.getFirstName(),
                        restRequest.getLastName(),
                        restRequest.getRole(),
                        restRequest.getUsername(),
                        restRequest.getPasswordHash());
        final UpdateUserResponse response = userApiCaller.updateUser(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        LOG.debug("DELETE /user/{}", id);

        final DeleteUserRequest request = new DeleteUserRequest(new UserId(id));
        userApiCaller.deleteUser(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UsersResponse>> users() {
        LOG.debug("GET /users");

        final List<UsersResponse> response = userApiCaller.users();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/user-todos", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserTodosResponse>> userTodos(
            @RequestParam("userId") Long userId,
            @RequestParam(value = "fields", required = false) List<TodoUserTodosSortField> fields,
            @RequestParam(value = "directions", required = false) List<Order> directions) {
        LOG.debug("GET /user-todos");

        final UserTodosRequest request = new UserTodosRequest(new UserId(userId), fields, directions);
        final List<UserTodosResponse> response = userApiCaller.userTodos(request);
        return ResponseEntity.ok().body(response);
    }
}
