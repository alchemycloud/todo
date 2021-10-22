package com.todo.backend.rest.todoapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import com.todo.backend.api.dto.todoapi.TodosResponse;
import java.util.List;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class TodoApi_TodosTest extends AbstractApiTest {

    @Test
    public void testTodos_todos() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        final List<TodosResponse> response = todos();

        // expected results
        assertObject(response);
    }
}
