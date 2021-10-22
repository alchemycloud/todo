package com.todo.backend.rest.todoapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import com.todo.backend.api.dto.todoapi.ReadTodoResponse;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class TodoApi_ReadTodoTest extends AbstractApiTest {

    @Test
    public void testReadTodo_readTodo() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        final ReadTodoResponse response = readTodo(null);

        // expected results
        assertObject(response);
    }
}
