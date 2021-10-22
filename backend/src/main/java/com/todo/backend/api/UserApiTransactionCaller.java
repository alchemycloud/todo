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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserApiTransactionCaller {
    private static final Logger LOG = LoggerFactory.getLogger(UserApiTransactionCaller.class);

    @Inject private AuditFacade auditFacade;

    @Inject private UserApi userApi;

    @Transactional(readOnly = true)
    public ReadUserResponse readUser(ReadUserRequest dto) {
        LOG.trace("readUser {}", dto.getId());

        final ReadUserResponse result = userApi.readUser(dto);
        auditFacade.flushInTransaction();
        return result;
    }

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest dto) {
        LOG.trace("createUser");

        final CreateUserResponse result = userApi.createUser(dto);
        auditFacade.flushInTransaction();
        return result;
    }

    @Transactional
    public UpdateUserResponse updateUser(UpdateUserRequest dto) {
        LOG.trace("updateUser {}", dto.getId());

        final UpdateUserResponse result = userApi.updateUser(dto);
        auditFacade.flushInTransaction();
        return result;
    }

    @Transactional
    public void deleteUser(DeleteUserRequest dto) {
        LOG.trace("deleteUser {}", dto.getId());

        userApi.deleteUser(dto);
        auditFacade.flushInTransaction();
    }

    @Transactional(readOnly = true)
    public List<UsersResponse> users() {
        LOG.trace("users");

        final List<UsersResponse> result = userApi.users();
        auditFacade.flushInTransaction();
        return result;
    }

    @Transactional(readOnly = true)
    public List<UserTodosResponse> userTodos(UserTodosRequest dto) {
        LOG.trace("userTodos {}", dto.getUserId());

        final List<UserTodosResponse> result = userApi.userTodos(dto);
        auditFacade.flushInTransaction();
        return result;
    }
}
