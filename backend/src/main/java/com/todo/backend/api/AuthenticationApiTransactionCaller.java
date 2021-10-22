package com.todo.backend.api;

import com.todo.backend.api.dto.authenticationapi.ChangePasswordRequest;
import com.todo.backend.api.dto.authenticationapi.RefreshTokenRequest;
import com.todo.backend.api.dto.authenticationapi.SignInRequest;
import com.todo.backend.api.dto.authenticationapi.SignInResponse;
import com.todo.backend.api.dto.authenticationapi.SignUpRequest;
import com.todo.backend.audit.AuditFacade;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationApiTransactionCaller {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationApiTransactionCaller.class);

    @Inject private AuditFacade auditFacade;

    @Inject private AuthenticationApi authenticationApi;

    @Transactional
    public SignInResponse refreshToken(RefreshTokenRequest dto) {
        LOG.trace("refreshToken");

        final SignInResponse result = authenticationApi.refreshToken(dto);
        auditFacade.flushInTransaction();
        return result;
    }

    @Transactional
    public void signUp(SignUpRequest dto) {
        LOG.trace("signUp");

        authenticationApi.signUp(dto);
        auditFacade.flushInTransaction();
    }

    @Transactional
    public SignInResponse signIn(SignInRequest dto) {
        LOG.trace("signIn");

        final SignInResponse result = authenticationApi.signIn(dto);
        auditFacade.flushInTransaction();
        return result;
    }

    @Transactional
    public void changePassword(ChangePasswordRequest dto) {
        LOG.trace("changePassword");

        authenticationApi.changePassword(dto);
        auditFacade.flushInTransaction();
    }
}
