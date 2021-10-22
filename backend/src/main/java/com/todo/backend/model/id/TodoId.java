package com.todo.backend.model.id;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

@JsonDeserialize(using = TodoIdDeserializer.class)
public class TodoId extends AbstractId {
    private static final long serialVersionUID = 1L;

    public TodoId() {
        super();
    }

    public TodoId(Long id) {
        super(id);
    }
}

class TodoIdDeserializer extends StdDeserializer<TodoId> {
    private static final long serialVersionUID = 1L;

    public TodoIdDeserializer() {
        this(null);
    }

    public TodoIdDeserializer(Class<TodoId> t) {
        super(t);
    }

    @Override
    public TodoId deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException {
        return new TodoId(Long.valueOf(jsonparser.getText()));
    }
}
