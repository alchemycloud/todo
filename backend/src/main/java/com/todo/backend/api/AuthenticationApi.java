package com.todo.backend.api;

import com.todo.backend.api.dto.authenticationapi.ChangePasswordRequest;
import com.todo.backend.api.dto.authenticationapi.RefreshTokenRequest;
import com.todo.backend.api.dto.authenticationapi.SignInRequest;
import com.todo.backend.api.dto.authenticationapi.SignInResponse;
import com.todo.backend.api.dto.authenticationapi.SignUpRequest;
import com.todo.backend.exception.ExceptionType;
import com.todo.backend.model.User;
import com.todo.backend.model.enumeration.UserRole;
import com.todo.backend.model.id.UserId;
import com.todo.backend.repository.UserRepository;
import com.todo.backend.security.JWTService;
import com.todo.backend.service.SecurityService;
import com.todo.backend.util.Require;
import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.Optional;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationApi {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationApi.class);

    @Inject private JWTService jwtService;

    @Inject private UserRepository userRepository;

    @Inject private PasswordEncoder passwordEncoder;

    @Inject private SecurityService securityService;

    public SignInResponse refreshToken(RefreshTokenRequest dto) {
        LOG.trace("refreshToken");

        final Claims claims = jwtService.getJwtClaims(dto.getRefreshToken());
        Require.badRequestUnless(!claims.getExpiration().before(new Date()), ExceptionType.REFRESH_TOKEN_IS_EXPIRED);

        final User result = userRepository.findById(new UserId(Long.valueOf(claims.getSubject()))).get();
        return signInResponse(result, Optional.empty());
    }

    public void signUp(SignUpRequest dto) {
        LOG.trace("signUp");

        final UserRole role = UserRole.ADMIN;
        final String passwordHash = passwordEncoder.encode(dto.getPassword());

        final String firstName = dto.getFirstName();
        final String lastName = dto.getLastName();
        final String username = dto.getUsername(); // model.setPassword(); // TODO set this field manually
        final User model = userRepository.create(null, null, null, null, null);
    }

    public SignInResponse signIn(SignInRequest dto) {
        LOG.trace("signIn");

        final Optional<User> user = userRepository.findByUsername(dto.getUsername());

        if (user.isEmpty()) return null;
        if (!passwordEncoder.matches(dto.getPassword(), user.get().getPasswordHash())) return null;

        final User result = userRepository.findById(user.get().getId()).get();
        return signInResponse(result, Optional.empty());
    }

    public void changePassword(ChangePasswordRequest dto) {
        LOG.trace("changePassword");
        // TODO check security constraints

        final User principal = securityService.getPrincipal().get();

        Require.badRequestUnless(principal != null, ExceptionType.CREDENTIALS_ARE_INVALID);
        Require.badRequestUnless(passwordEncoder.matches(dto.getOldPassword(), principal.getPasswordHash()), ExceptionType.CREDENTIALS_ARE_INVALID);

        principal.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.update(principal);
    }

    private SignInResponse signInResponse(User model, Optional<String> oldRefreshToken) {

        final String responseAccessToken = jwtService.createAccessToken(model, model.getRole());
        final String responseRefreshToken = oldRefreshToken.orElseGet(() -> jwtService.createRefreshToken(model));

        final UserId responseId = model.getId();
        final String responseFirstName = model.getFirstName();
        final String responseLastName = model.getLastName();
        final UserRole responseRole = model.getRole();
        final String responseUsername = model.getUsername();
        return new SignInResponse(responseAccessToken, responseRefreshToken, responseId, responseFirstName, responseLastName, responseRole, responseUsername);
    }
}
