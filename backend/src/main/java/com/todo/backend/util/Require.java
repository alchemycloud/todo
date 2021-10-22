package com.todo.backend.util;

import com.todo.backend.exception.BadRequestException;
import com.todo.backend.exception.ExceptionType;
import com.todo.backend.exception.UnauthorizedException;

public final class Require {

    private Require() {
        // private constructor to prevent instantiation
    }

    public static void badRequestUnless(boolean condition, ExceptionType type) {
        if (!condition) throw new BadRequestException(type);
    }

    public static void accessDeniedUnless(boolean condition, ExceptionType type) {
        if (!condition) throw new UnauthorizedException(type);
    }
}
