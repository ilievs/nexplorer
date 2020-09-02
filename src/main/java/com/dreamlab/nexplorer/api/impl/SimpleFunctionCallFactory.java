package com.dreamlab.nexplorer.api.impl;

import com.dreamlab.nexplorer.api.FunctionCall;
import com.dreamlab.nexplorer.api.FunctionCallFactory;
import com.dreamlab.nexplorer.exception.UnknownFunctionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SimpleFunctionCallFactory implements FunctionCallFactory {

    private final Map<String, FunctionCall> functionMapping;

    @Autowired
    public SimpleFunctionCallFactory(List<FunctionCall> functionCalls) {
        functionMapping = functionCalls.stream()
                .collect(Collectors.toMap(FunctionCall::getFunctionName, Function.identity()));
    }

    @Override
    public FunctionCall create(String functionName) {
        return Optional.ofNullable(functionMapping.get(functionName))
                .orElseThrow(() ->  new UnknownFunctionException(functionName));
    }
}
