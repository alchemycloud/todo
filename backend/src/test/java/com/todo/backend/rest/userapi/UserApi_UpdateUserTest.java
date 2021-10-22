package com.todo.backend.rest.userapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import com.todo.backend.api.dto.userapi.UpdateUserResponse;
import com.todo.backend.rest.dto.userapi.RestUpdateUserRequest;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class UserApi_UpdateUserTest extends AbstractApiTest {

    @Test
    public void testUpdateUser_updateUser() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        final UpdateUserResponse response = updateUser(null, new RestUpdateUserRequest(null, null, null, null, null));

        // expected results
        assertObject(response);
    }
}
