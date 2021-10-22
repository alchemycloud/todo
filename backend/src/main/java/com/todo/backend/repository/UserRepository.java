package com.todo.backend.repository;

import com.todo.backend.model.User;
import com.todo.backend.model.enumeration.UserRole;
import com.todo.backend.model.id.UserId;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends SimpleRepository<User, UserId> {

    User create(String firstName, String lastName, UserRole role, String username, String passwordHash);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    List<User> findByRole(UserRole role);

    Optional<User> findByUsername(String username);

    List<User> findByPasswordHash(String passwordHash);

    List<User> users();
}
