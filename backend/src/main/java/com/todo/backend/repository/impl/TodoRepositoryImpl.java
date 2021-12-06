package com.todo.backend.repository.impl;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.todo.backend.model.QTodo;
import com.todo.backend.model.QUser;
import com.todo.backend.model.Todo;
import com.todo.backend.model.User;
import com.todo.backend.model.enumeration.Status;
import com.todo.backend.model.enumeration.TodoUserTodosSortField;
import com.todo.backend.model.id.TodoId;
import com.todo.backend.model.id.UserId;
import com.todo.backend.repository.AbstractSimpleRepositoryImpl;
import com.todo.backend.repository.TodoRepository;
import com.todo.backend.repository.tuple.TodoTodosTuple;
import com.todo.backend.repository.util.OrderableField;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepositoryImpl extends AbstractSimpleRepositoryImpl<Todo, TodoId> implements TodoRepository {
    private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryImpl.class);

    @Override
    protected Class<Todo> getDomainClass() {
        return Todo.class;
    }

    @Override
    protected EntityPath<Todo> getEntityPathBase() {
        return QTodo.todo;
    }

    @Override
    public NumberPath<Long> getEntityIdPathBase() {
        return QTodo.todo.id;
    }

    @Override
    protected TodoId getId(Todo entity) {
        return entity.getId();
    }

    @Override
    public Todo create(User user, String task, ZonedDateTime date, Status status) {
        final Todo todo = new Todo();
        todo.setUser(user);
        todo.setTask(task);
        todo.setDate(date);
        todo.setStatus(status);
        entityManager.persist(todo);
        postSave(todo);
        return todo;
    }

    @Override
    public List<Todo> findByTask(String task) {
        LOG.trace(".findByTask()");
        final QTodo qTodo = QTodo.todo;
        return factory.select(qTodo).from(qTodo).where(qTodo.task.eq(task)).orderBy(qTodo.id.asc().nullsLast()).fetch();
    }

    @Override
    public List<Todo> findByDate(ZonedDateTime date) {
        LOG.trace(".findByDate()");
        final QTodo qTodo = QTodo.todo;
        return factory.select(qTodo).from(qTodo).where(qTodo.date.eq(date)).orderBy(qTodo.id.asc().nullsLast()).fetch();
    }

    @Override
    public List<Todo> findByStatus(Status status) {
        LOG.trace(".findByStatus()");
        final QTodo qTodo = QTodo.todo;
        return factory.select(qTodo).from(qTodo).where(qTodo.status.eq(status)).orderBy(qTodo.id.asc().nullsLast()).fetch();
    }

    @Override
    public List<Todo> userTodos(UserId userId, List<OrderableField<TodoUserTodosSortField>> orderableFields) {
        LOG.trace(".userTodos(userId: {})", userId);
        final QTodo qTodo = QTodo.todo;
        final List<OrderSpecifier<?>> orderByFields = new ArrayList();
        orderableFields.forEach(
                orderable ->
                        orderByFields.add(new OrderSpecifier<>(orderable.getDirection(), Expressions.stringPath(orderable.getField().toString())).nullsLast()));
        orderByFields.add(qTodo.id.asc().nullsLast());
        return factory.select(qTodo).from(qTodo).where(qTodo.user.id.eq(userId.getValue())).orderBy(orderByFields.toArray(OrderSpecifier<?>[]::new)).fetch();
    }

    @Override
    public List<TodoTodosTuple> todos() {
        LOG.trace(".todos()");
        final QTodo qTodo = QTodo.todo;
        final QUser qUser = QUser.user;
        return factory.select(qTodo, qUser).from(qTodo).innerJoin(qTodo.user, qUser).orderBy(qTodo.id.asc().nullsLast()).fetch().stream()
                .map(t -> new TodoTodosTuple(t.get(qTodo), t.get(qUser)))
                .toList();
    }

    @Override
    public List<Todo> findAllById(Collection<TodoId> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        return factory.select(getEntityPathBase()).from(QTodo.todo).where(QTodo.todo.id.in(ids.stream().map(TodoId::getValue).toList())).fetch();
    }
}
