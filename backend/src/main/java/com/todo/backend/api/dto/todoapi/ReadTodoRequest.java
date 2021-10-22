package com.todo.backend.api.dto.todoapi;

import cloud.alchemy.fabut.property.PropertyPath;
import com.todo.backend.model.id.TodoId;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class ReadTodoRequest implements Serializable {

    private static final long serialVersionUID = 1;

    public static final PropertyPath<TodoId> ID = new PropertyPath<>("id");

    @NotNull private TodoId id;

    private ReadTodoRequest() {}

    public ReadTodoRequest(TodoId id) {
        this();
        this.id = id;
    }

    public final TodoId getId() {
        return id;
    }

    public final ReadTodoRequest setId(TodoId id) {
        this.id = id;
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
        if (ReadTodoRequest.class != obj.getClass()) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ReadTodoRequest[" + "this.id=" + this.id + "]";
    }
}
