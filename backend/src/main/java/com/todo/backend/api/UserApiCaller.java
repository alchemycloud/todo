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
import com.todo.backend.audit.AuditFacade;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class UserApiCaller {
    private static final Logger LOG = LoggerFactory.getLogger(UserApiCaller.class);

    @Lazy @Inject private AuditFacade auditFacade;

    @Inject private UserApiTransactionCaller userApiTransactionCaller;

    public ReadUserResponse readUser(ReadUserRequest dto) {
        LOG.trace("readUser {}", dto.getId());

        final ReadUserResponse result = userApiTransactionCaller.readUser(dto);
        auditFacade.flushAfterTransaction();
        return result;
    }

    public CreateUserResponse createUser(CreateUserRequest dto) {
        LOG.trace("createUser");

        final CreateUserResponse result = userApiTransactionCaller.createUser(dto);
        auditFacade.flushAfterTransaction();
        return result;
    }

    public UpdateUserResponse updateUser(UpdateUserRequest dto) {
        LOG.trace("updateUser {}", dto.getId());

        final UpdateUserResponse result = userApiTransactionCaller.updateUser(dto);
        auditFacade.flushAfterTransaction();
        return result;
    }

    public void deleteUser(DeleteUserRequest dto) {
        LOG.trace("deleteUser {}", dto.getId());

        userApiTransactionCaller.deleteUser(dto);
        auditFacade.flushAfterTransaction();
    }

    public List<UsersResponse> users() {
        LOG.trace("users");

        final List<UsersResponse> result = userApiTransactionCaller.users();
        auditFacade.flushAfterTransaction();
        return result;
    }

    public List<UserTodosResponse> userTodos(UserTodosRequest dto) {
        LOG.trace("userTodos {}", dto.getUserId());

        final List<UserTodosResponse> result = userApiTransactionCaller.userTodos(dto);
        auditFacade.flushAfterTransaction();
        return result;
    }
}
