package com.odk.pjt.dicematchbe.exception;

public class DuplicatedLoginSessionException extends DiceMatchException {
    public DuplicatedLoginSessionException() {
        super();
    }

    public DuplicatedLoginSessionException(String message) {
        super(message);
    }

    public DuplicatedLoginSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedLoginSessionException(Throwable cause) {
        super(cause);
    }

    protected DuplicatedLoginSessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
