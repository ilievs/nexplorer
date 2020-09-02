package com.dreamlab.nexplorer.api.impl;

import com.dreamlab.nexplorer.api.FunctionCall;
import com.dreamlab.nexplorer.exception.IncorrectArgumentFormatException;
import com.dreamlab.nexplorer.exception.WrongNumberOfArgumentsException;
import com.dreamlab.nexplorer.service.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Component
public class NextPrimeFunctionCall implements FunctionCall {

    public static final String FUNCTION_CALL = "nextPrime";

    private final PrimeNumberService primeNumberService;

    @Autowired
    public NextPrimeFunctionCall(PrimeNumberService primeNumberService) {
        Objects.requireNonNull(primeNumberService);
        this.primeNumberService = primeNumberService;
    }

    private BigInteger validate(List<String> args) {
        if (args.size() != 1) {
            throw new WrongNumberOfArgumentsException(FUNCTION_CALL, 1, args);
        }
        String argument1 = args.get(0);
        try {
            return new BigInteger(argument1);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentFormatException(FUNCTION_CALL, 1, argument1);
        }
    }

    @Override
    public String execute(List<String> args) {
        BigInteger arg = validate(args);
        return primeNumberService.nextPrime(arg).toString();
    }
}
