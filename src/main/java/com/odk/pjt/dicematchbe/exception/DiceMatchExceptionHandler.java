package com.odk.pjt.dicematchbe.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DiceMatchExceptionHandler {

    @ExceptionHandler(DiceMatchException.class)
    public ExceptionResponse handle(DiceMatchException exception) {
        ExceptionResponse exceptionResponse = getExceptionResponse(exception);
        exceptionResponse.setCategory(ExceptionCategory.EXPECTED);
        return exceptionResponse;
    }

    @ExceptionHandler(Exception.class)
    public ExceptionResponse handle(Exception exception) {
        ExceptionResponse exceptionResponse = getExceptionResponse(exception);
        exceptionResponse.setCategory(ExceptionCategory.UNKNOWN);
        return exceptionResponse;
    }

    private ExceptionResponse getExceptionResponse(Throwable throwable) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(throwable.getMessage());

        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
            sb.append(stackTraceElement.toString()).append("\n");
        }

        exceptionResponse.setTrace(sb.toString());
        return exceptionResponse;
    }
}
