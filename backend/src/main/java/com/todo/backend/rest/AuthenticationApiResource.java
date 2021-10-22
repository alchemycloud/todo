package com.todo.backend.rest;

import com.todo.backend.api.AuthenticationApiCaller;
import com.todo.backend.api.dto.authenticationapi.ChangePasswordRequest;
import com.todo.backend.api.dto.authenticationapi.RefreshTokenRequest;
import com.todo.backend.api.dto.authenticationapi.SignInRequest;
import com.todo.backend.api.dto.authenticationapi.SignInResponse;
import com.todo.backend.api.dto.authenticationapi.SignUpRequest;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AuthenticationApiResource {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationApiResource.class);

    @Inject private AuthenticationApiCaller authenticationApiCaller;

    @PostMapping(value = "/refresh-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignInResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        LOG.debug("POST /refresh-token {}", request);

        final SignInResponse response = authenticationApiCaller.refreshToken(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest request) {
        LOG.debug("POST /sign-up {}", request);

        authenticationApiCaller.signUp(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest request) {
        LOG.debug("POST /sign-in {}", request);

        final SignInResponse response = authenticationApiCaller.signIn(request);
        if (response != null) return ResponseEntity.ok().body(response);
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/change-password", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        LOG.debug("POST /change-password {}", request);

        authenticationApiCaller.changePassword(request);
        return ResponseEntity.ok().build();
    }
}
