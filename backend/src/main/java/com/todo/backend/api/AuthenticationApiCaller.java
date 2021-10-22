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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationApiCaller {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationApiCaller.class);

    @Lazy @Inject private AuditFacade auditFacade;

    @Inject private AuthenticationApiTransactionCaller authenticationApiTransactionCaller;

    public SignInResponse refreshToken(RefreshTokenRequest dto) {
        LOG.trace("refreshToken");

        final SignInResponse result = authenticationApiTransactionCaller.refreshToken(dto);
        auditFacade.flushAfterTransaction();
        return result;
    }

    public void signUp(SignUpRequest dto) {
        LOG.trace("signUp");

        authenticationApiTransactionCaller.signUp(dto);
        auditFacade.flushAfterTransaction();
    }

    public SignInResponse signIn(SignInRequest dto) {
        LOG.trace("signIn");

        final SignInResponse result = authenticationApiTransactionCaller.signIn(dto);
        auditFacade.flushAfterTransaction();
        return result;
    }

    public void changePassword(ChangePasswordRequest dto) {
        LOG.trace("changePassword");

        authenticationApiTransactionCaller.changePassword(dto);
        auditFacade.flushAfterTransaction();
    }
}
