package com.odk.pjt.dicematchbe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DiceMatchExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(DiceMatchExceptionHandler.class);

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DiceMatchException.class)
    public ExceptionResponse handle(DiceMatchException exception) {
        logger.error(exception.getMessage(), exception);
        ExceptionResponse exceptionResponse = getExceptionResponse(exception);
        exceptionResponse.setCategory(ExceptionCategory.EXPECTED);
        return exceptionResponse;
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handle(Exception exception) {
        logger.error(exception.getMessage(), exception);
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
