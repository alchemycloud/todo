package com.todo.backend.api.dto.userapi;

import cloud.alchemy.fabut.property.PropertyPath;
import com.todo.backend.model.enumeration.UserRole;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequest implements Serializable {

    private static final long serialVersionUID = 1;

    public static final PropertyPath<String> FIRST_NAME = new PropertyPath<>("firstName");
    public static final PropertyPath<String> LAST_NAME = new PropertyPath<>("lastName");
    public static final PropertyPath<UserRole> ROLE = new PropertyPath<>("role");
    public static final PropertyPath<String> USERNAME = new PropertyPath<>("username");
    public static final PropertyPath<String> PASSWORD_HASH = new PropertyPath<>("passwordHash");

    @NotNull
    @Size(min = 1, max = 40)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 60)
    private String lastName;

    @NotNull private UserRole role;

    @NotNull
    @Size(min = 3, max = 128)
    private String username;

    @NotNull
    @Size(min = 6, max = 128)
    private String passwordHash;

    private CreateUserRequest() {}

    public CreateUserRequest(String firstName, String lastName, UserRole role, String username, String passwordHash) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final CreateUserRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public final String getLastName() {
        return lastName;
    }

    public final CreateUserRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public final UserRole getRole() {
        return role;
    }

    public final CreateUserRequest setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public final String getUsername() {
        return username;
    }

    public final CreateUserRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public final String getPasswordHash() {
        return passwordHash;
    }

    public final CreateUserRequest setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (CreateUserRequest.class != obj.getClass()) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
        result = PRIME * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
        result = PRIME * result + ((this.role == null) ? 0 : this.role.hashCode());
        result = PRIME * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = PRIME * result + ((this.passwordHash == null) ? 0 : this.passwordHash.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "CreateUserRequest["
                + "this.firstName="
                + this.firstName
                + ", this.lastName="
                + this.lastName
                + ", this.role="
                + this.role
                + ", this.username="
                + this.username
                + "]";
    }
}
