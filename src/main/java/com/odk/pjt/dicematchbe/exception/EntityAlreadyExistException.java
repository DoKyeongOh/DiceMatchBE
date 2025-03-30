package com.odk.pjt.dicematchbe.exception;

public class EntityAlreadyExistException extends DiceMatchException {
    public EntityAlreadyExistException(String message) {
        super(message);
    }

    public EntityAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
