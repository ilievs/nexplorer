package com.dreamlab.nexplorer.api.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import com.dreamlab.nexplorer.api.FunctionCall;
import com.dreamlab.nexplorer.exception.IncorrectArgumentFormatException;
import com.dreamlab.nexplorer.exception.WrongNumberOfArgumentsException;
import com.dreamlab.nexplorer.service.PrimeNumberService;

import org.springframework.stereotype.Component;

@Component
public class NextPrimeFunctionCall implements FunctionCall {

    public static final String FUNCTION_NAME = "nextPrime";

    private final PrimeNumberService primeNumberService;

    public NextPrimeFunctionCall(PrimeNumberService primeNumberService) {
        this.primeNumberService = Objects.requireNonNull(primeNumberService);
    }

    private BigInteger validate(List<String> args) {
        if (args.size() != 1) {
            throw new WrongNumberOfArgumentsException(FUNCTION_NAME, 1, args);
        }
        String argument1 = args.getFirst();
        try {
            return new BigInteger(argument1);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentFormatException(FUNCTION_NAME, 1, argument1);
        }
    }

    @Override
    public String getFunctionName() {
        return FUNCTION_NAME;
    }

    @Override
    public String execute(List<String> args) {
        BigInteger arg = validate(args);
        return primeNumberService.nextPrime(arg).toString();
    }
}
