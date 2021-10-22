package com.todo.backend.api.dto.authenticationapi;

import cloud.alchemy.fabut.property.PropertyPath;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignInRequest implements Serializable {

    private static final long serialVersionUID = 1;

    public static final PropertyPath<String> USERNAME = new PropertyPath<>("username");
    public static final PropertyPath<String> PASSWORD = new PropertyPath<>("password");

    @NotNull
    @Size(min = 3, max = 128)
    private String username;

    @NotNull
    @Size(min = 6, max = 255)
    private String password;

    private SignInRequest() {}

    public SignInRequest(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public final String getUsername() {
        return username;
    }

    public final SignInRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public final String getPassword() {
        return password;
    }

    public final SignInRequest setPassword(String password) {
        this.password = password;
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
        if (SignInRequest.class != obj.getClass()) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = PRIME * result + ((this.password == null) ? 0 : this.password.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SignInRequest[" + "this.username=" + this.username + "]";
    }
}
