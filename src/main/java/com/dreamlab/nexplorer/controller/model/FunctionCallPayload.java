package com.dreamlab.nexplorer.controller.model;

import java.util.List;
import java.util.Objects;

public record FunctionCallPayload(String functionName, List<String> arguments) {

    public FunctionCallPayload(String functionName, List<String> arguments) {
        this.functionName = Objects.requireNonNull(functionName);
        this.arguments = Objects.requireNonNull(arguments);
    }
}
