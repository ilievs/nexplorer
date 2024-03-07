package com.dreamlab.nexplorer.controller.model;

import java.util.Objects;

public record FunctionCallResultPayload(String result) {

    public FunctionCallResultPayload(String result) {
        this.result = Objects.requireNonNull(result);
    }
}
