package com.todo.backend.rest.authenticationapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import com.todo.backend.api.dto.authenticationapi.SignInRequest;
import com.todo.backend.api.dto.authenticationapi.SignInResponse;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class AuthenticationApi_SignInTest extends AbstractApiTest {

    @Test
    public void testSignIn_signIn() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        final SignInResponse response = signIn(new SignInRequest(null, null));

        // expected results
        assertObject(response);
    }
}
