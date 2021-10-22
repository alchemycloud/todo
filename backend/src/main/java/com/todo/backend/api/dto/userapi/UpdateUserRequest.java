package com.todo.backend.api.dto.userapi;

import cloud.alchemy.fabut.property.PropertyPath;
import com.todo.backend.model.enumeration.UserRole;
import com.todo.backend.model.id.UserId;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserRequest implements Serializable {

    private static final long serialVersionUID = 1;

    public static final PropertyPath<UserId> ID = new PropertyPath<>("id");
    public static final PropertyPath<String> FIRST_NAME = new PropertyPath<>("firstName");
    public static final PropertyPath<String> LAST_NAME = new PropertyPath<>("lastName");
    public static final PropertyPath<UserRole> ROLE = new PropertyPath<>("role");
    public static final PropertyPath<String> USERNAME = new PropertyPath<>("username");
    public static final PropertyPath<String> PASSWORD_HASH = new PropertyPath<>("passwordHash");

    @NotNull private UserId id;

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

    private UpdateUserRequest() {}

    public UpdateUserRequest(UserId id, String firstName, String lastName, UserRole role, String username, String passwordHash) {
        this();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public final UserId getId() {
        return id;
    }

    public final UpdateUserRequest setId(UserId id) {
        this.id = id;
        return this;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final UpdateUserRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public final String getLastName() {
        return lastName;
    }

    public final UpdateUserRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public final UserRole getRole() {
        return role;
    }

    public final UpdateUserRequest setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public final String getUsername() {
        return username;
    }

    public final UpdateUserRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public final String getPasswordHash() {
        return passwordHash;
    }

    public final UpdateUserRequest setPasswordHash(String passwordHash) {
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
        if (UpdateUserRequest.class != obj.getClass()) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = PRIME * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
        result = PRIME * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
        result = PRIME * result + ((this.role == null) ? 0 : this.role.hashCode());
        result = PRIME * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = PRIME * result + ((this.passwordHash == null) ? 0 : this.passwordHash.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UpdateUserRequest["
                + "this.id="
                + this.id
                + ", this.firstName="
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
