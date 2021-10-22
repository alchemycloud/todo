package com.todo.backend.rest.dto.todoapi;

import cloud.alchemy.fabut.property.PropertyPath;
import com.todo.backend.model.enumeration.Status;
import com.todo.backend.model.id.UserId;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RestUpdateTodoRequest implements Serializable {

    private static final long serialVersionUID = 1;

    public static final PropertyPath<UserId> USER_ID = new PropertyPath<>("userId");
    public static final PropertyPath<String> TASK = new PropertyPath<>("task");
    public static final PropertyPath<ZonedDateTime> DATE = new PropertyPath<>("date");
    public static final PropertyPath<Status> STATUS = new PropertyPath<>("status");

    @NotNull private UserId userId;

    @NotNull
    @Size(min = 1, max = 255)
    private String task;

    @NotNull private ZonedDateTime date;

    @NotNull private Status status;

    private RestUpdateTodoRequest() {}

    public RestUpdateTodoRequest(UserId userId, String task, ZonedDateTime date, Status status) {
        this();
        this.userId = userId;
        this.task = task;
        this.date = date;
        this.status = status;
    }

    public final UserId getUserId() {
        return userId;
    }

    public final RestUpdateTodoRequest setUserId(UserId userId) {
        this.userId = userId;
        return this;
    }

    public final String getTask() {
        return task;
    }

    public final RestUpdateTodoRequest setTask(String task) {
        this.task = task;
        return this;
    }

    public final ZonedDateTime getDate() {
        return date;
    }

    public final RestUpdateTodoRequest setDate(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public final Status getStatus() {
        return status;
    }

    public final RestUpdateTodoRequest setStatus(Status status) {
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
        if (RestUpdateTodoRequest.class != obj.getClass()) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = PRIME * result + ((this.task == null) ? 0 : this.task.hashCode());
        result = PRIME * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = PRIME * result + ((this.status == null) ? 0 : this.status.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "RestUpdateTodoRequest["
                + "this.userId="
                + this.userId
                + ", this.task="
                + this.task
                + ", this.date="
                + this.date
                + ", this.status="
                + this.status
                + "]";
    }
}
