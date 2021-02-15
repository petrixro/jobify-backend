package com.jobifyProject.jobify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(UUID id) {
        super(MessageFormat.format("Company with id {0} not found", id));
    }
}
