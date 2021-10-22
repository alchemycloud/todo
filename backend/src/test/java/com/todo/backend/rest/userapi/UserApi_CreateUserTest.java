package com.todo.backend.rest.userapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import com.todo.backend.api.dto.userapi.CreateUserRequest;
import com.todo.backend.api.dto.userapi.CreateUserResponse;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class UserApi_CreateUserTest extends AbstractApiTest {

    @Test
    public void testCreateUser_createUser() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        final CreateUserResponse response = createUser(new CreateUserRequest(null, null, null, null, null));

        // expected results
        assertObject(response);
    }
}
