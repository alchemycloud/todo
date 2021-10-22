package com.todo.backend.rest.authenticationapi;

import static org.junit.jupiter.api.Assertions.fail;

import com.todo.backend.AbstractApiTest;
import com.todo.backend.api.dto.authenticationapi.RefreshTokenRequest;
import com.todo.backend.api.dto.authenticationapi.SignInResponse;
import org.junit.jupiter.api.Test;

@org.junit.jupiter.api.Tag("ApiTestCategory1")
public class AuthenticationApi_RefreshTokenTest extends AbstractApiTest {

    @Test
    public void testRefreshToken_refreshToken() throws Exception {
        fail("Test is not implemented");
        // preconditions

        // test
        takeSnapshot();
        final SignInResponse response = refreshToken(new RefreshTokenRequest(null));

        // expected results
        assertObject(response);
    }
}
