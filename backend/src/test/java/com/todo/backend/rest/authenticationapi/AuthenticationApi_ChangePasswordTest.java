package com.todo.backend.rest.authenticationapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import com.todo.backend.api.dto.authenticationapi.ChangePasswordRequest;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class AuthenticationApi_ChangePasswordTest extends AbstractApiTest {

    @Test
    public void testChangePassword_changePassword() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        changePassword(new ChangePasswordRequest(null, null));

        // expected results

    }
}
