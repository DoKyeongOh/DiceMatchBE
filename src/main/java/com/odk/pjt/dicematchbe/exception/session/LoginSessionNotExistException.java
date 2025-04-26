package com.odk.pjt.dicematchbe.exception.session;

import com.odk.pjt.dicematchbe.exception.DiceMatchException;

public class LoginSessionNotExistException extends DiceMatchException {
    public LoginSessionNotExistException() {
        super();
    }

    public LoginSessionNotExistException(String message) {
        super(message);
    }

    public LoginSessionNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginSessionNotExistException(Throwable cause) {
        super(cause);
    }

    protected LoginSessionNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
