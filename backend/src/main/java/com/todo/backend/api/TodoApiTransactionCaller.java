package com.todo.backend.api;

import com.todo.backend.api.dto.todoapi.CreateTodoRequest;
import com.todo.backend.api.dto.todoapi.CreateTodoResponse;
import com.todo.backend.api.dto.todoapi.DeleteTodoRequest;
import com.todo.backend.api.dto.todoapi.ReadTodoRequest;
import com.todo.backend.api.dto.todoapi.ReadTodoResponse;
import com.todo.backend.api.dto.todoapi.TodosResponse;
import com.todo.backend.api.dto.todoapi.UpdateTodoRequest;
import com.todo.backend.api.dto.todoapi.UpdateTodoResponse;
import com.todo.backend.audit.AuditFacade;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoApiTransactionCaller {
    private static final Logger LOG = LoggerFactory.getLogger(TodoApiTransactionCaller.class);

    @Inject private AuditFacade auditFacade;

    @Inject private TodoApi todoApi;

    @Transactional(readOnly = true)
    public ReadTodoResponse readTodo(ReadTodoRequest dto) {
        LOG.trace("readTodo {}", dto.getId());

        final ReadTodoResponse result = todoApi.readTodo(dto);
        auditFacade.flushInTransaction();
        return result;
    }

    @Transactional
    public CreateTodoResponse createTodo(CreateTodoRequest dto) {
        LOG.trace("createTodo {}", dto.getUserId());

        final CreateTodoResponse result = todoApi.createTodo(dto);
        auditFacade.flushInTransaction();
        return result;
    }

    @Transactional
    public UpdateTodoResponse updateTodo(UpdateTodoRequest dto) {
        LOG.trace("updateTodo {} {}", dto.getId(), dto.getUserId());

        final UpdateTodoResponse result = todoApi.updateTodo(dto);
        auditFacade.flushInTransaction();
        return result;
    }

    @Transactional
    public void deleteTodo(DeleteTodoRequest dto) {
        LOG.trace("deleteTodo {}", dto.getId());

        todoApi.deleteTodo(dto);
        auditFacade.flushInTransaction();
    }

    @Transactional(readOnly = true)
    public List<TodosResponse> todos() {
        LOG.trace("todos");

        final List<TodosResponse> result = todoApi.todos();
        auditFacade.flushInTransaction();
        return result;
    }
}
