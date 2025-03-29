package com.odk.pjt.dicematchbe.exception;

public class DiceMatchException extends Exception {
    public DiceMatchException() {
        super();
    }

    public DiceMatchException(String message) {
        super(message);
    }

    public DiceMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiceMatchException(Throwable cause) {
        super(cause);
    }

    protected DiceMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
