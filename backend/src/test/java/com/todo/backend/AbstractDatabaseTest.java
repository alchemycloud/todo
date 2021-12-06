package com.todo.backend;

import cloud.alchemy.fabut.Fabut;
import com.todo.backend.api.dto.authenticationapi.ChangePasswordRequest;
import com.todo.backend.api.dto.authenticationapi.RefreshTokenRequest;
import com.todo.backend.api.dto.authenticationapi.SignInRequest;
import com.todo.backend.api.dto.authenticationapi.SignInResponse;
import com.todo.backend.api.dto.authenticationapi.SignUpRequest;
import com.todo.backend.api.dto.todoapi.CreateTodoRequest;
import com.todo.backend.api.dto.todoapi.CreateTodoResponse;
import com.todo.backend.api.dto.todoapi.DeleteTodoRequest;
import com.todo.backend.api.dto.todoapi.ReadTodoRequest;
import com.todo.backend.api.dto.todoapi.ReadTodoResponse;
import com.todo.backend.api.dto.todoapi.TodosResponse;
import com.todo.backend.api.dto.todoapi.UpdateTodoRequest;
import com.todo.backend.api.dto.todoapi.UpdateTodoResponse;
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
import com.todo.backend.model.DataProcessorLog;
import com.todo.backend.model.DataVersion;
import com.todo.backend.model.Todo;
import com.todo.backend.model.User;
import com.todo.backend.model.id.AbstractId;
import com.todo.backend.model.id.DataProcessorLogId;
import com.todo.backend.model.id.DataVersionId;
import com.todo.backend.model.id.TodoId;
import com.todo.backend.model.id.UserId;
import com.todo.backend.repository.DataProcessorLogRepository;
import com.todo.backend.repository.DataVersionRepository;
import com.todo.backend.repository.TodoRepository;
import com.todo.backend.repository.UserRepository;
import com.todo.backend.rest.dto.todoapi.RestUpdateTodoRequest;
import com.todo.backend.rest.dto.userapi.RestUpdateUserRequest;
import com.todo.backend.util.AppThreadLocals;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles(resolver = TestProfileResolver.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@Transactional
public abstract class AbstractDatabaseTest extends Fabut {
    @Inject protected UserRepository userRepository;

    @Inject protected TodoRepository todoRepository;

    @Inject protected DataVersionRepository dataVersionRepository;

    @Inject protected DataProcessorLogRepository dataProcessorLogRepository;

    public AbstractDatabaseTest() {
        complexTypes.add(RefreshTokenRequest.class);
        complexTypes.add(SignInResponse.class);
        complexTypes.add(SignUpRequest.class);
        complexTypes.add(SignInRequest.class);
        complexTypes.add(SignInResponse.class);
        complexTypes.add(ChangePasswordRequest.class);
        complexTypes.add(ReadUserRequest.class);
        complexTypes.add(ReadUserResponse.class);
        complexTypes.add(CreateUserRequest.class);
        complexTypes.add(CreateUserResponse.class);
        complexTypes.add(UpdateUserRequest.class);
        complexTypes.add(UpdateUserResponse.class);
        complexTypes.add(RestUpdateUserRequest.class);
        complexTypes.add(DeleteUserRequest.class);
        complexTypes.add(UsersResponse.class);
        complexTypes.add(UserTodosRequest.class);
        complexTypes.add(UserTodosResponse.class);
        complexTypes.add(ReadTodoRequest.class);
        complexTypes.add(ReadTodoResponse.class);
        complexTypes.add(CreateTodoRequest.class);
        complexTypes.add(CreateTodoResponse.class);
        complexTypes.add(UpdateTodoRequest.class);
        complexTypes.add(UpdateTodoResponse.class);
        complexTypes.add(RestUpdateTodoRequest.class);
        complexTypes.add(DeleteTodoRequest.class);
        complexTypes.add(TodosResponse.class);
        entityTypes.add(User.class);
        entityTypes.add(Todo.class);
        entityTypes.add(DataVersion.class);
        entityTypes.add(DataProcessorLog.class);
    }

    @BeforeEach
    public void before() {
        AppThreadLocals.initialize(Optional.of(UUID.randomUUID().toString()), Optional.empty());
        AppThreadLocals.setCountQueries(false);
        AppThreadLocals.setQueryNumberForTest(0);

        super.before();
    }

    @AfterEach
    public void after() {
        final StringBuilder messageBuilder = new StringBuilder();
        AppThreadLocals.initialize(Optional.of(UUID.randomUUID().toString()), Optional.empty());
        AppThreadLocals.clearCountQueries();
        final Integer actualQueryCount = AppThreadLocals.getQueryCount();
        final Integer queryNumberForTest = AppThreadLocals.getQueryNumberForTest();
        AppThreadLocals.clearQueryNumberForTest();
        AppThreadLocals.clearQueryCount();

        if (!queryNumberForTest.equals(actualQueryCount)) {
            messageBuilder
                    .append("Number of queries for test method is not correct. Expected: ")
                    .append(queryNumberForTest)
                    .append(", but was: ")
                    .append(actualQueryCount);
        }
        super.after();
    }

    @Override
    public void customAssertEquals(Object expected, Object actual) {

        if (expected instanceof final ZonedDateTime expectedTime && actual instanceof final ZonedDateTime actualTime) {
            assertTimeWithMargin(expectedTime, actualTime);
        } else if (expected instanceof final AbstractId expectedId && actual instanceof final AbstractId actualId) {
            assertEquals(expectedId.getValue(), actualId.getValue());

        } else {
            assertEquals(expected, actual);
        }
    }

    public void assertTimeWithMargin(ZonedDateTime expectedTime, ZonedDateTime actualTime) {
        final long timeMargin = 60000;
        final long expectedMilli = expectedTime.toInstant().toEpochMilli();
        final long actualMili = actualTime.toInstant().toEpochMilli();
        if (expectedMilli > actualMili) assertTrue(expectedMilli - actualMili < timeMargin);
        else assertTrue(actualMili - expectedMilli < timeMargin);
    }

    @Override
    public List<?> findAll(Class<?> clazz) {
        if (clazz == User.class) {
            return userRepository.findAll();
        } else if (clazz == Todo.class) {
            return todoRepository.findAll();
        } else if (clazz == DataVersion.class) {
            return dataVersionRepository.findAll();
        } else if (clazz == DataProcessorLog.class) {
            return dataProcessorLogRepository.findAll();
        }

        throw new IllegalStateException("No findAll for class: " + clazz.getName());
    }

    @Override
    public Object findById(final Class<?> entityClass, final Object id) {
        if (entityClass == User.class) {
            return userRepository.findById((UserId) id).orElse(null);
        } else if (entityClass == Todo.class) {
            return todoRepository.findById((TodoId) id).orElse(null);
        } else if (entityClass == DataVersion.class) {
            return dataVersionRepository.findById((DataVersionId) id).orElse(null);
        } else if (entityClass == DataProcessorLog.class) {
            return dataProcessorLogRepository.findById((DataProcessorLogId) id).orElse(null);
        }

        throw new IllegalStateException("No findById for class: " + entityClass.getName());
    }
}
