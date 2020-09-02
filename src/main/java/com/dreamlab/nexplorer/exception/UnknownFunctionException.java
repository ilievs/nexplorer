package com.dreamlab.nexplorer.exception;

public class UnknownFunctionException extends RuntimeException {

    public UnknownFunctionException(String functionName) {
        super(String.format("Call to unknown function '%s'", functionName));
    }
}
