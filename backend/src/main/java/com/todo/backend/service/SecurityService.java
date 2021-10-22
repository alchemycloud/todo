package com.todo.backend.service;

import com.todo.backend.model.User;
import com.todo.backend.repository.UserRepository;
import com.todo.backend.util.AppThreadLocals;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Inject private UserRepository userRepository;

    public Optional<User> getPrincipal() {

        final Optional<User> alreadyLoadedPrincipal = AppThreadLocals.getPrincipal();

        if (alreadyLoadedPrincipal.isPresent()) {
            return alreadyLoadedPrincipal;
        }
        if (AppThreadLocals.getPrincipalUsername().isPresent()) {
            Optional<User> principal = userRepository.findByUsername(AppThreadLocals.getPrincipalUsername().get());
            AppThreadLocals.setPrincipal(principal.get());
            return principal;
        }
        return Optional.empty();
    }
}
