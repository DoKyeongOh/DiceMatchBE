package com.odk.pjt.dicematchbe.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionResponse {
    private String message;
    private String trace;
    private ExceptionCategory category;
}
