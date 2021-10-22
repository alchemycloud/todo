package com.todo.backend.rest.todoapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import com.todo.backend.api.dto.todoapi.CreateTodoRequest;
import com.todo.backend.api.dto.todoapi.CreateTodoResponse;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class TodoApi_CreateTodoTest extends AbstractApiTest {

    @Test
    public void testCreateTodo_createTodo() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        final CreateTodoResponse response = createTodo(new CreateTodoRequest(null, null, null, null));

        // expected results
        assertObject(response);
    }
}
