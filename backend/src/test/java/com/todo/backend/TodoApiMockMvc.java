package com.todo.backend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.backend.api.dto.todoapi.CreateTodoRequest;
import com.todo.backend.api.dto.todoapi.CreateTodoResponse;
import com.todo.backend.api.dto.todoapi.ReadTodoResponse;
import com.todo.backend.api.dto.todoapi.TodosResponse;
import com.todo.backend.api.dto.todoapi.UpdateTodoResponse;
import com.todo.backend.model.id.TodoId;
import com.todo.backend.rest.dto.todoapi.RestUpdateTodoRequest;
import com.todo.backend.util.AppThreadLocals;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.server.ResponseStatusException;

public interface TodoApiMockMvc {

    MockMvc getMockMvc();

    ObjectMapper getObjectMapper();

    AbstractApiTest getAbstractApiTest();

    byte[] convertObjectToJsonBytes(Object object) throws IOException;

    default ReadTodoResponse readTodo(TodoId id) throws Exception {

        MockHttpServletRequestBuilder builder =
                get("/api" + "/todo/" + id + "").contentType(MediaType.APPLICATION_JSON).header("x-timezone", "Europe/Belgrade");

        final MvcResult result = getMockMvc().perform(builder.accept(MediaType.APPLICATION_JSON)).andReturn();
        final RestResponse<ReadTodoResponse> restResponse = RestResponse.fromMvcResult(result, getObjectMapper(), ReadTodoResponse.class);
        AppThreadLocals.setCountQueries(false);
        if (restResponse.getStatus() != HttpStatus.OK.value()) {
            throw new ResponseStatusException(HttpStatus.valueOf(restResponse.getStatus()));
        }
        return restResponse.getData();
    }

    default CreateTodoResponse createTodo(CreateTodoRequest request) throws Exception {

        MockHttpServletRequestBuilder builder = post("/api" + "/todo").contentType(MediaType.APPLICATION_JSON).header("x-timezone", "Europe/Belgrade");

        final MvcResult result = getMockMvc().perform(builder.content(convertObjectToJsonBytes(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
        final RestResponse<CreateTodoResponse> restResponse = RestResponse.fromMvcResult(result, getObjectMapper(), CreateTodoResponse.class);
        AppThreadLocals.setCountQueries(false);
        if (restResponse.getStatus() != HttpStatus.OK.value()) {
            throw new ResponseStatusException(HttpStatus.valueOf(restResponse.getStatus()));
        }
        return restResponse.getData();
    }

    default UpdateTodoResponse updateTodo(TodoId id, RestUpdateTodoRequest request) throws Exception {

        MockHttpServletRequestBuilder builder =
                put("/api" + "/todo/" + id + "").contentType(MediaType.APPLICATION_JSON).header("x-timezone", "Europe/Belgrade");

        final MvcResult result = getMockMvc().perform(builder.content(convertObjectToJsonBytes(request)).accept(MediaType.APPLICATION_JSON)).andReturn();
        final RestResponse<UpdateTodoResponse> restResponse = RestResponse.fromMvcResult(result, getObjectMapper(), UpdateTodoResponse.class);
        AppThreadLocals.setCountQueries(false);
        if (restResponse.getStatus() != HttpStatus.OK.value()) {
            throw new ResponseStatusException(HttpStatus.valueOf(restResponse.getStatus()));
        }
        return restResponse.getData();
    }

    default void deleteTodo(TodoId id) throws Exception {

        MockHttpServletRequestBuilder builder =
                delete("/api" + "/todo/" + id + "").contentType(MediaType.APPLICATION_JSON).header("x-timezone", "Europe/Belgrade");

        final MvcResult result = getMockMvc().perform(builder.accept(MediaType.APPLICATION_JSON)).andReturn();
        final RestResponse<Void> restResponse = RestResponse.fromMvcResult(result, getObjectMapper(), Void.class);
        AppThreadLocals.setCountQueries(false);
        if (restResponse.getStatus() != HttpStatus.OK.value()) {
            throw new ResponseStatusException(HttpStatus.valueOf(restResponse.getStatus()));
        }
    }

    private List<TodosResponse> todos(String accessToken) throws Exception {

        MockHttpServletRequestBuilder builder =
                get("/api" + "/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-timezone", "Europe/Belgrade")
                        .header("Authorization", "Bearer " + accessToken);

        final MvcResult result = getMockMvc().perform(builder.accept(MediaType.APPLICATION_JSON)).andReturn();
        final RestResponse<List<TodosResponse>> restResponse = RestResponse.fromMvcResult(result, getObjectMapper(), List.class, TodosResponse.class);
        AppThreadLocals.setCountQueries(false);
        if (restResponse.getStatus() != HttpStatus.OK.value()) {
            throw new ResponseStatusException(HttpStatus.valueOf(restResponse.getStatus()));
        }
        return restResponse.getData();
    }

    default List<TodosResponse> todos() throws Exception {
        return todos(getAbstractApiTest().getCurrentUserAccessToken());
    }
}
