package com.todo.backend.model;

import cloud.alchemy.fabut.property.PropertyPath;
import com.todo.backend.model.enumeration.UserRole;
import com.todo.backend.model.id.UserId;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<UserId> ID = new PropertyPath<>("id");
    public static final PropertyPath<String> FIRST_NAME = new PropertyPath<>("firstName");
    public static final PropertyPath<String> LAST_NAME = new PropertyPath<>("lastName");
    public static final PropertyPath<UserRole> ROLE = new PropertyPath<>("role");
    public static final PropertyPath<String> USERNAME = new PropertyPath<>("username");
    public static final PropertyPath<String> PASSWORD_HASH = new PropertyPath<>("passwordHash");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "firstName")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "lastName")
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @NotNull
    @Size(min = 3, max = 128)
    @Column(name = "username")
    private String username;

    @NotNull
    @Size(min = 6, max = 128)
    @Column(name = "passwordHash")
    private String passwordHash;

    public User() {}

    public UserId getId() {
        if (this.id == null) {
            return null;
        } else {
            return new UserId(this.id);
        }
    }

    public void setId(UserId id) {
        this.id = id.getValue();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        return Objects.equals(this.getId(), ((User) obj).getId());
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = PRIME + User.class.hashCode();

        if (this.getId() != null) {
            return PRIME * result + this.getId().hashCode();
        }

        return result;
    }

    @Override
    public String toString() {
        return "User["
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
