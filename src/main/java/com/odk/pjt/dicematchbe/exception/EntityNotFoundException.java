package com.odk.pjt.dicematchbe.exception;

public class EntityNotFoundException extends DiceMatchException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
