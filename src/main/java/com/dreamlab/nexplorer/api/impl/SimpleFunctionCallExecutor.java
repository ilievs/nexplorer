package com.dreamlab.nexplorer.api.impl;

import com.dreamlab.nexplorer.api.FunctionCallFactory;
import com.dreamlab.nexplorer.api.FunctionCallExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SimpleFunctionCallExecutor implements FunctionCallExecutor {

    private final FunctionCallFactory functionCallFactory;

    @Autowired
    public SimpleFunctionCallExecutor(FunctionCallFactory factory) {
        Objects.requireNonNull(factory);
        this.functionCallFactory = factory;
    }

    @Override
    public String execute(String functionName, List<String> arguments) {
        return functionCallFactory.create(functionName).execute(arguments);
    }
}
