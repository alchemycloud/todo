package com.todo.backend.rest.userapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import com.todo.backend.api.dto.userapi.UsersResponse;
import java.util.List;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class UserApi_UsersTest extends AbstractApiTest {

    @Test
    public void testUsers_users() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        final List<UsersResponse> response = users();

        // expected results
        assertObject(response);
    }
}
