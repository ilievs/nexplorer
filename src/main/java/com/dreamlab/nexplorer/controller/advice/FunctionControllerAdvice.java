package com.dreamlab.nexplorer.controller.advice;

import com.dreamlab.nexplorer.exception.UnknownFunctionException;
import com.dreamlab.nexplorer.exception.WrongNumberOfArgumentsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class FunctionControllerAdvice {

    @ExceptionHandler({ UnknownFunctionException.class, WrongNumberOfArgumentsException.class })
    public ResponseEntity<Object> handleFunctionCallException(Exception e, WebRequest request) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> asd(IllegalArgumentException e) {
        if (e.getMessage().equals("Integer too large for implementation")) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

        return ResponseEntity.internalServerError().build();
    }
}
