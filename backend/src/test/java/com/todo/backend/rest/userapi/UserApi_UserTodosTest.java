package com.todo.backend.rest.userapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import com.todo.backend.api.dto.userapi.UserTodosResponse;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class UserApi_UserTodosTest extends AbstractApiTest {

    @Test
    public void testUserTodos_userTodos() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        final List<UserTodosResponse> response = userTodos(null, Collections.emptyList(), Collections.emptyList());

        // expected results
        assertObject(response);
    }
}
