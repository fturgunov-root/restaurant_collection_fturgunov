package com.example.restaurant_collection_fturgunov.common.controller.handler;

import com.example.restaurant_collection_fturgunov.common.controller.handler.pojo.FieldErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    FieldErrorResponse handle(IllegalArgumentException ex) {
        return FieldErrorResponse.builder()
                .message(ex.getMessage())
                .code("IllegalArgument")
                .build();
    }
}