package com.todo.backend.api.dto.authenticationapi;

import cloud.alchemy.fabut.property.PropertyPath;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpRequest implements Serializable {

    private static final long serialVersionUID = 1;

    public static final PropertyPath<String> FIRST_NAME = new PropertyPath<>("firstName");
    public static final PropertyPath<String> LAST_NAME = new PropertyPath<>("lastName");
    public static final PropertyPath<String> USERNAME = new PropertyPath<>("username");
    public static final PropertyPath<String> PASSWORD = new PropertyPath<>("password");

    @NotNull
    @Size(min = 1, max = 40)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 60)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 128)
    private String username;

    @NotNull
    @Size(min = 12, max = 128)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){12,128}$")
    private String password;

    private SignUpRequest() {}

    public SignUpRequest(String firstName, String lastName, String username, String password) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final SignUpRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public final String getLastName() {
        return lastName;
    }

    public final SignUpRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public final String getUsername() {
        return username;
    }

    public final SignUpRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public final String getPassword() {
        return password;
    }

    public final SignUpRequest setPassword(String password) {
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
        if (SignUpRequest.class != obj.getClass()) {
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
        result = PRIME * result + ((this.username == null) ? 0 : this.username.hashCode());
        result = PRIME * result + ((this.password == null) ? 0 : this.password.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SignUpRequest[" + "this.firstName=" + this.firstName + ", this.lastName=" + this.lastName + ", this.username=" + this.username + "]";
    }
}
