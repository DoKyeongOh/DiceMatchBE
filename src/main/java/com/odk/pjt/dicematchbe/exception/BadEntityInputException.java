package com.odk.pjt.dicematchbe.exception;

public class BadEntityInputException extends DiceMatchException {
    public BadEntityInputException(String message) {
        super(message);
    }

    public BadEntityInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadEntityInputException(Throwable cause) {
        super(cause);
    }
}
