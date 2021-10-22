package com.todo.backend.rest.todoapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import com.todo.backend.api.dto.todoapi.UpdateTodoResponse;
import com.todo.backend.rest.dto.todoapi.RestUpdateTodoRequest;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class TodoApi_UpdateTodoTest extends AbstractApiTest {

    @Test
    public void testUpdateTodo_updateTodo() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        final UpdateTodoResponse response = updateTodo(null, new RestUpdateTodoRequest(null, null, null, null));

        // expected results
        assertObject(response);
    }
}
