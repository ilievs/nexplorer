package com.dreamlab.nexplorer.controller.model;

import java.util.Objects;

public class FunctionCallResultPayload {

    private final String result;

    public FunctionCallResultPayload(String result) {
        this.result = Objects.requireNonNull(result);
    }

    public String getResult() {
        return result;
    }
}
