package com.todo.backend.audit;

import com.todo.backend.util.AppThreadLocals;
import java.util.Optional;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AuditFacade {
    @Lazy @Inject private AuditFacade auditFacade;

    private static final Logger LOG = LoggerFactory.getLogger(AuditFacade.class);

    public void flushInTransaction() {
        final long start = System.currentTimeMillis();

        final long end = System.currentTimeMillis();
        LOG.trace("Flushing in transaction took: {} ms", end - start);
    }

    public void flushAfterTransaction() {
        final String correlationId = AppThreadLocals.getCorrelationId();
        final Optional<String> principalUsername = AppThreadLocals.getPrincipalUsername();
        auditFacade.flushAfterTransaction(correlationId, principalUsername);
    }

    @Async
    public void flushAfterTransaction(String correlationId, Optional<String> principalUsername) {
        final long start = System.currentTimeMillis();
        AppThreadLocals.initialize(Optional.of(correlationId), principalUsername);

        final long end = System.currentTimeMillis();
        LOG.trace("Flushing in transaction took: {} ms", end - start);
    }
}
