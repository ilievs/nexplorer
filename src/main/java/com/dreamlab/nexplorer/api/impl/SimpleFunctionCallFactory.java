package com.dreamlab.nexplorer.api.impl;

import com.dreamlab.nexplorer.api.FunctionCall;
import com.dreamlab.nexplorer.api.FunctionCallFactory;
import com.dreamlab.nexplorer.exception.UnknownFunctionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class SimpleFunctionCallFactory implements FunctionCallFactory {

    private final Map<String, FunctionCall> functionMapping;

    @Autowired
    public SimpleFunctionCallFactory(IsPrimeFunctionCall isPrime, NextPrimeFunctionCall nextPrime) {
        functionMapping = Map.of(
                IsPrimeFunctionCall.FUNCTION_CALL, isPrime,
                NextPrimeFunctionCall.FUNCTION_CALL, nextPrime);
    }

    @Override
    public FunctionCall create(String functionName) {
        return Optional.ofNullable(functionMapping.get(functionName))
                .orElseThrow(() ->  new UnknownFunctionException(functionName));
    }
}
