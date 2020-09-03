package com.dreamlab.nexplorer.controller.model;

import java.util.List;
import java.util.Objects;

public class FunctionCallPayload {

    private final String functionName;
    private final List<String> arguments;

    public FunctionCallPayload(String functionName, List<String> arguments) {
        this.functionName = Objects.requireNonNull(functionName);
        this.arguments = Objects.requireNonNull(arguments);
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
