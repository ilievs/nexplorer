package com.dreamlab.nexplorer.api.impl;

import java.util.List;
import java.util.Objects;

import com.dreamlab.nexplorer.api.FunctionCallExecutor;
import com.dreamlab.nexplorer.api.FunctionCallFactory;

import org.springframework.stereotype.Service;

@Service
public class SimpleFunctionCallExecutor implements FunctionCallExecutor {

    private final FunctionCallFactory functionCallFactory;

    public SimpleFunctionCallExecutor(FunctionCallFactory factory) {
        this.functionCallFactory = Objects.requireNonNull(factory);
    }

    @Override
    public String execute(String functionName, List<String> arguments) {
        return functionCallFactory.create(functionName).execute(arguments);
    }
}
