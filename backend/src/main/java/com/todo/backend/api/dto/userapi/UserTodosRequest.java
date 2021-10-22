package com.todo.backend.api.dto.userapi;

import cloud.alchemy.fabut.property.PropertyPath;
import com.querydsl.core.types.Order;
import com.todo.backend.model.enumeration.TodoUserTodosSortField;
import com.todo.backend.model.id.UserId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

public class UserTodosRequest implements Serializable {

    private static final long serialVersionUID = 1;

    public static final PropertyPath<UserId> USER_ID = new PropertyPath<>("userId");
    public static final PropertyPath<List<TodoUserTodosSortField>> FIELDS = new PropertyPath<>("fields");
    public static final PropertyPath<List<Order>> DIRECTIONS = new PropertyPath<>("directions");

    @NotNull private UserId userId;

    private List<TodoUserTodosSortField> fields = new ArrayList<>();

    private List<Order> directions = new ArrayList<>();

    private UserTodosRequest() {}

    public UserTodosRequest(UserId userId, List<TodoUserTodosSortField> fields, List<Order> directions) {
        this();
        this.userId = userId;
        this.fields = fields;
        this.directions = directions;
    }

    public final UserId getUserId() {
        return userId;
    }

    public final UserTodosRequest setUserId(UserId userId) {
        this.userId = userId;
        return this;
    }

    public final List<TodoUserTodosSortField> getFields() {
        return fields;
    }

    public final UserTodosRequest setFields(List<TodoUserTodosSortField> fields) {
        this.fields = fields;
        return this;
    }

    public final List<Order> getDirections() {
        return directions;
    }

    public final UserTodosRequest setDirections(List<Order> directions) {
        this.directions = directions;
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
        if (UserTodosRequest.class != obj.getClass()) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UserTodosRequest[" + "this.userId=" + this.userId + ", this.fields=" + this.fields + ", this.directions=" + this.directions + "]";
    }
}
