package com.todo.backend.api;

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
import com.todo.backend.model.Todo;
import com.todo.backend.model.User;
import com.todo.backend.model.enumeration.Status;
import com.todo.backend.model.enumeration.TodoUserTodosSortField;
import com.todo.backend.model.enumeration.UserRole;
import com.todo.backend.model.id.TodoId;
import com.todo.backend.model.id.UserId;
import com.todo.backend.repository.TodoRepository;
import com.todo.backend.repository.UserRepository;
import com.todo.backend.repository.util.OrderableField;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserApi {
    private static final Logger LOG = LoggerFactory.getLogger(UserApi.class);

    @Inject private UserRepository userRepository;

    @Inject private TodoRepository todoRepository;

    public ReadUserResponse readUser(ReadUserRequest dto) {
        LOG.trace("readUser {}", dto.getId());
        // TODO check security constraints(id)

        final User model = userRepository.getOne(dto.getId());
        final UserId responseId = model.getId();
        final String responseFirstName = model.getFirstName();
        final String responseLastName = model.getLastName();
        final UserRole responseRole = model.getRole();
        final String responseUsername = model.getUsername();
        final String responsePasswordHash = model.getPasswordHash();
        return new ReadUserResponse(responseId, responseFirstName, responseLastName, responseRole, responseUsername, responsePasswordHash);
    }

    public CreateUserResponse createUser(CreateUserRequest dto) {
        LOG.trace("createUser");
        // TODO check security constraints

        final String firstName = dto.getFirstName();
        final String lastName = dto.getLastName();
        final UserRole role = dto.getRole();
        final String username = dto.getUsername();
        final String passwordHash = dto.getPasswordHash();
        final User model = userRepository.create(null, null, null, null, null);

        final UserId responseId = model.getId();
        final String responseFirstName = model.getFirstName();
        final String responseLastName = model.getLastName();
        final UserRole responseRole = model.getRole();
        final String responseUsername = model.getUsername();
        final String responsePasswordHash = model.getPasswordHash();
        return new CreateUserResponse(responseId, responseFirstName, responseLastName, responseRole, responseUsername, responsePasswordHash);
    }

    public UpdateUserResponse updateUser(UpdateUserRequest dto) {
        LOG.trace("updateUser {}", dto.getId());
        // TODO check security constraints(id)

        final User model = userRepository.getOne(dto.getId());
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setRole(dto.getRole());
        model.setUsername(dto.getUsername());
        model.setPasswordHash(dto.getPasswordHash());
        userRepository.update(model);

        final UserId responseId = model.getId();
        final String responseFirstName = model.getFirstName();
        final String responseLastName = model.getLastName();
        final UserRole responseRole = model.getRole();
        final String responseUsername = model.getUsername();
        final String responsePasswordHash = model.getPasswordHash();
        return new UpdateUserResponse(responseId, responseFirstName, responseLastName, responseRole, responseUsername, responsePasswordHash);
    }

    public void deleteUser(DeleteUserRequest dto) {
        LOG.trace("deleteUser {}", dto.getId());
        // TODO check security constraints(id)

        userRepository.deleteById(dto.getId());
    }

    public List<UsersResponse> users() {
        LOG.trace("users");
        // TODO check security constraints

        final List<User> models = userRepository.users();
        return models.stream()
                .map(
                        model -> {
                            final UserId responseId = model.getId();
                            final String responseFirstName = model.getFirstName();
                            final String responseLastName = model.getLastName();
                            final UserRole responseRole = model.getRole();
                            final String responseUsername = model.getUsername();
                            final String responsePasswordHash = model.getPasswordHash();
                            return new UsersResponse(responseId, responseFirstName, responseLastName, responseRole, responseUsername, responsePasswordHash);
                        })
                .collect(Collectors.toList());
    }

    public List<UserTodosResponse> userTodos(UserTodosRequest dto) {
        LOG.trace("userTodos {}", dto.getUserId());
        // TODO check security constraints(userId)

        final List<OrderableField<TodoUserTodosSortField>> orderableFields = OrderableField.createOrderableFields(dto.getFields(), dto.getDirections());
        final List<Todo> models = todoRepository.userTodos(dto.getUserId(), orderableFields);
        return models.stream()
                .map(
                        model -> {
                            final TodoId responseId = model.getId();
                            final UserId responseUserId = model.getUserId();
                            final String responseTask = model.getTask();
                            final ZonedDateTime responseDate = model.getDate();
                            final Status responseStatus = model.getStatus();
                            return new UserTodosResponse(responseId, responseUserId, responseTask, responseDate, responseStatus);
                        })
                .collect(Collectors.toList());
    }
}
