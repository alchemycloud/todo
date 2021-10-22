package com.todo.backend.rest.todoapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class TodoApi_DeleteTodoTest extends AbstractApiTest {

    @Test
    public void testDeleteTodo_deleteTodo() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        deleteTodo(null);

        // expected results

    }
}
