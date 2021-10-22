package com.todo.backend.model;

import cloud.alchemy.fabut.property.PropertyPath;
import com.todo.backend.model.enumeration.Status;
import com.todo.backend.model.id.TodoId;
import com.todo.backend.model.id.UserId;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

@Entity
@Table(name = "Todo")
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final PropertyPath<TodoId> ID = new PropertyPath<>("id");
    public static final PropertyPath<User> USER = new PropertyPath<>("user");
    public static final PropertyPath<String> TASK = new PropertyPath<>("task");
    public static final PropertyPath<ZonedDateTime> DATE = new PropertyPath<>("date");
    public static final PropertyPath<Status> STATUS = new PropertyPath<>("status");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "task")
    private String task;

    @NotNull
    @Column(name = "date")
    private ZonedDateTime date;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Todo() {}

    public TodoId getId() {
        if (this.id == null) {
            return null;
        } else {
            return new TodoId(this.id);
        }
    }

    public void setId(TodoId id) {
        this.id = id.getValue();
    }

    public UserId getUserId() {
        if (user instanceof HibernateProxy) {
            LazyInitializer lazyInitializer = ((HibernateProxy) user).getHibernateLazyInitializer();
            if (lazyInitializer.isUninitialized()) {
                return new UserId((Long) lazyInitializer.getIdentifier());
            }
        }
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Todo)) {
            return false;
        }
        return Objects.equals(this.getId(), ((Todo) obj).getId());
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = PRIME + Todo.class.hashCode();

        if (this.getId() != null) {
            return PRIME * result + this.getId().hashCode();
        }

        return result;
    }

    @Override
    public String toString() {
        return "Todo["
                + "this.id="
                + this.id
                + ", this.userId="
                + this.getUserId()
                + ", this.task="
                + this.task
                + ", this.date="
                + this.date
                + ", this.status="
                + this.status
                + "]";
    }
}
