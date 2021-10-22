package com.todo.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.Order;
import com.todo.backend.api.dto.userapi.CreateUserRequest;
import com.todo.backend.api.dto.userapi.CreateUserResponse;
import com.todo.backend.api.dto.userapi.ReadUserResponse;
import com.todo.backend.api.dto.userapi.UpdateUserResponse;
import com.todo.backend.api.dto.userapi.UserTodosResponse;
import com.todo.backend.api.dto.userapi.UsersResponse;
import com.todo.backend.model.enumeration.TodoUserTodosSortField;
import com.todo.backend.model.id.UserId;
import com.todo.backend.rest.dto.userapi.RestUpdateUserRequest;
import com.todo.backend.util.AppThreadLocals;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.server.ResponseStatusException;

public interface UserApiMockMvc {

    MockMvc getMockMvc();

    ObjectMapper getObjectMapper();

    AbstractApiTest getAbstractApiTest();

    byte[] convertObjectToJsonBytes(Object object) throws IOException;

    private ReadUserResponse readUser(UserId id, String accessToken) throws Exception {

        MockHttpServletRequestBuilder builder =
                get("/api" + "/user/" + id + "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-timezone", "Europe/Belgrade")
                        .header("Authorization", "Bearer " + accessToken);

        final MvcResult result = getMockMvc().perform(builder.accept(MediaType.APPLICATION_JSON)).andReturn();
        final RestResponse<ReadUserResponse> restResponse = RestResponse.fromMvcResult(result, getObjectMapper(), ReadUserResponse.class);
        AppThreadLocals.setCountQueries(false);
        if (restResponse.getStatus() != HttpStatus.OK.value()) {
            throw new ResponseStatusException(HttpStatus.valueOf(restResponse.getStatus()));
        }
        return restResponse.getData();
    }

    default ReadUserResponse readUser(UserId id) throws Exception {
        return readUser(id, getAbstractApiTest().getCurrentUserAccessToken());
    }

    private CreateUserResponse createUser(CreateUserRequest request, String accessToken) throws Exception {

        MockHttpServletRequestBuilder builder =
                post("/api" + "/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-timezone", "Europe/Belgrade")
                        .header("Authorization", "Bearer " + accessToken);

        final MvcResult result = getMockMvc().perform(builder.content(convertObjectToJsonBytes(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
        final RestResponse<CreateUserResponse> restResponse = RestResponse.fromMvcResult(result, getObjectMapper(), CreateUserResponse.class);
        AppThreadLocals.setCountQueries(false);
        if (restResponse.getStatus() != HttpStatus.OK.value()) {
            throw new ResponseStatusException(HttpStatus.valueOf(restResponse.getStatus()));
        }
        return restResponse.getData();
    }

    default CreateUserResponse createUser(CreateUserRequest request) throws Exception {
        return createUser(request, getAbstractApiTest().getCurrentUserAccessToken());
    }

    private UpdateUserResponse updateUser(UserId id, RestUpdateUserRequest request, String accessToken) throws Exception {

        MockHttpServletRequestBuilder builder =
                put("/api" + "/user/" + id + "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-timezone", "Europe/Belgrade")
                        .header("Authorization", "Bearer " + accessToken);

        final MvcResult result = getMockMvc().perform(builder.content(convertObjectToJsonBytes(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
        final RestResponse<UpdateUserResponse> restResponse = RestResponse.fromMvcResult(result, getObjectMapper(), UpdateUserResponse.class);
        AppThreadLocals.setCountQueries(false);
        if (restResponse.getStatus() != HttpStatus.OK.value()) {
            throw new ResponseStatusException(HttpStatus.valueOf(restResponse.getStatus()));
        }
        return restResponse.getData();
    }

    default UpdateUserResponse updateUser(UserId id, RestUpdateUserRequest request) throws Exception {
        return updateUser(id, request, getAbstractApiTest().getCurrentUserAccessToken());
    }

    private void deleteUser(UserId id, String accessToken) throws Exception {

        MockHttpServletRequestBuilder builder =
                delete("/api" + "/user/" + id + "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-timezone", "Europe/Belgrade")
                        .header("Authorization", "Bearer " + accessToken);

        final MvcResult result = getMockMvc().perform(builder.accept(MediaType.APPLICATION_JSON)).andReturn();
        final RestResponse<Void> restResponse = RestResponse.fromMvcResult(result, getObjectMapper(), Void.class);
        AppThreadLocals.setCountQueries(false);
        if (restResponse.getStatus() != HttpStatus.OK.value()) {
            throw new ResponseStatusException(HttpStatus.valueOf(restResponse.getStatus()));
        }
    }

    default void deleteUser(UserId id) throws Exception {
        deleteUser(id, getAbstractApiTest().getCurrentUserAccessToken());
    }

    private List<UsersResponse> users(String accessToken) throws Exception {

        MockHttpServletRequestBuilder builder =
                get("/api" + "/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-timezone", "Europe/Belgrade")
                        .header("Authorization", "Bearer " + accessToken);

        final MvcResult result = getMockMvc().perform(builder.accept(MediaType.APPLICATION_JSON)).andReturn();
        final RestResponse<List<UsersResponse>> restResponse = RestResponse.fromMvcResult(result, getObjectMapper(), List.class, UsersResponse.class);
        AppThreadLocals.setCountQueries(false);
        if (restResponse.getStatus() != HttpStatus.OK.value()) {
            throw new ResponseStatusException(HttpStatus.valueOf(restResponse.getStatus()));
        }
        return restResponse.getData();
    }

    default List<UsersResponse> users() throws Exception {
        return users(getAbstractApiTest().getCurrentUserAccessToken());
    }

    private List<UserTodosResponse> userTodos(UserId userId, List<TodoUserTodosSortField> fields, List<Order> directions, String accessToken) throws Exception {

        MockHttpServletRequestBuilder builder =
                get("/api" + "/user-todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-timezone", "Europe/Belgrade")
                        .header("Authorization", "Bearer " + accessToken);

        builder = builder.param("userId", userId.getValue().toString());
        for (TodoUserTodosSortField value : fields) {
            builder = builder.param("fields", value.name());
        }
        for (Order value : directions) {
            builder = builder.param("directions", value.name());
        }

        final MvcResult result = getMockMvc().perform(builder.accept(MediaType.APPLICATION_JSON)).andReturn();
        final RestResponse<List<UserTodosResponse>> restResponse = RestResponse.fromMvcResult(result, getObjectMapper(), List.class, UserTodosResponse.class);
        AppThreadLocals.setCountQueries(false);
        if (restResponse.getStatus() != HttpStatus.OK.value()) {
            throw new ResponseStatusException(HttpStatus.valueOf(restResponse.getStatus()));
        }
        return restResponse.getData();
    }

    default List<UserTodosResponse> userTodos(UserId userId, List<TodoUserTodosSortField> fields, List<Order> directions) throws Exception {
        return userTodos(userId, fields, directions, getAbstractApiTest().getCurrentUserAccessToken());
    }
}
