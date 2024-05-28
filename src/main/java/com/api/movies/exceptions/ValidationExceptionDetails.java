package com.api.movies.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    // Campos adicionais
    private final String fields;
    private final String fieldsMessage;
}