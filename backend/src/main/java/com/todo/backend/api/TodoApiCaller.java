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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class TodoApiCaller {
    private static final Logger LOG = LoggerFactory.getLogger(TodoApiCaller.class);

    @Lazy @Inject private AuditFacade auditFacade;

    @Inject private TodoApiTransactionCaller todoApiTransactionCaller;

    public ReadTodoResponse readTodo(ReadTodoRequest dto) {
        LOG.trace("readTodo {}", dto.getId());

        final ReadTodoResponse result = todoApiTransactionCaller.readTodo(dto);
        auditFacade.flushAfterTransaction();
        return result;
    }

    public CreateTodoResponse createTodo(CreateTodoRequest dto) {
        LOG.trace("createTodo {}", dto.getUserId());

        final CreateTodoResponse result = todoApiTransactionCaller.createTodo(dto);
        auditFacade.flushAfterTransaction();
        return result;
    }

    public UpdateTodoResponse updateTodo(UpdateTodoRequest dto) {
        LOG.trace("updateTodo {} {}", dto.getId(), dto.getUserId());

        final UpdateTodoResponse result = todoApiTransactionCaller.updateTodo(dto);
        auditFacade.flushAfterTransaction();
        return result;
    }

    public void deleteTodo(DeleteTodoRequest dto) {
        LOG.trace("deleteTodo {}", dto.getId());

        todoApiTransactionCaller.deleteTodo(dto);
        auditFacade.flushAfterTransaction();
    }

    public List<TodosResponse> todos() {
        LOG.trace("todos");

        final List<TodosResponse> result = todoApiTransactionCaller.todos();
        auditFacade.flushAfterTransaction();
        return result;
    }
}
