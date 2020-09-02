package com.dreamlab.nexplorer.exception;

import java.util.List;

public class WrongNumberOfArgumentsException extends RuntimeException {

    public WrongNumberOfArgumentsException(String functionName, int expectedNumArgs, List<String> args) {
        super(String.format("Wrong number of arguments for function call to '%s'. Expected %d arguments, got %d (%s)",
                functionName, expectedNumArgs, args.size(), String.join(",", args)));
    }
}
