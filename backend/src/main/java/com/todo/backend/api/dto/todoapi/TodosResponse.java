package com.todo.backend.api.dto.todoapi;

import cloud.alchemy.fabut.property.PropertyPath;
import com.todo.backend.model.enumeration.Status;
import com.todo.backend.model.id.TodoId;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TodosResponse implements Serializable {

    private static final long serialVersionUID = 1;

    public static final PropertyPath<TodoId> ID = new PropertyPath<>("id");
    public static final PropertyPath<String> USER_USERNAME = new PropertyPath<>("userUsername");
    public static final PropertyPath<String> TASK = new PropertyPath<>("task");
    public static final PropertyPath<ZonedDateTime> DATE = new PropertyPath<>("date");
    public static final PropertyPath<Status> STATUS = new PropertyPath<>("status");

    @NotNull private TodoId id;

    @NotNull
    @Size(min = 3, max = 128)
    private String userUsername;

    @NotNull
    @Size(min = 1, max = 255)
    private String task;

    @NotNull private ZonedDateTime date;

    @NotNull private Status status;

    private TodosResponse() {}

    public TodosResponse(TodoId id, String userUsername, String task, ZonedDateTime date, Status status) {
        this();
        this.id = id;
        this.userUsername = userUsername;
        this.task = task;
        this.date = date;
        this.status = status;
    }

    public final TodoId getId() {
        return id;
    }

    public final TodosResponse setId(TodoId id) {
        this.id = id;
        return this;
    }

    public final String getUserUsername() {
        return userUsername;
    }

    public final TodosResponse setUserUsername(String userUsername) {
        this.userUsername = userUsername;
        return this;
    }

    public final String getTask() {
        return task;
    }

    public final TodosResponse setTask(String task) {
        this.task = task;
        return this;
    }

    public final ZonedDateTime getDate() {
        return date;
    }

    public final TodosResponse setDate(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public final Status getStatus() {
        return status;
    }

    public final TodosResponse setStatus(Status status) {
        this.status = status;
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
        if (TodosResponse.class != obj.getClass()) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = PRIME * result + ((this.userUsername == null) ? 0 : this.userUsername.hashCode());
        result = PRIME * result + ((this.task == null) ? 0 : this.task.hashCode());
        result = PRIME * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = PRIME * result + ((this.status == null) ? 0 : this.status.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "TodosResponse["
                + "this.id="
                + this.id
                + ", this.userUsername="
                + this.userUsername
                + ", this.task="
                + this.task
                + ", this.date="
                + this.date
                + ", this.status="
                + this.status
                + "]";
    }
}
