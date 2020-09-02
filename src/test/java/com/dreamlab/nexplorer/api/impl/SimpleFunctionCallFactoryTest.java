package com.dreamlab.nexplorer.api.impl;

import com.dreamlab.nexplorer.api.FunctionCall;
import com.dreamlab.nexplorer.api.FunctionCallFactory;
import com.dreamlab.nexplorer.exception.UnknownFunctionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimpleFunctionCallFactoryTest {

    FunctionCall isPrime = mock(IsPrimeFunctionCall.class);
    FunctionCall nextPrime = mock(NextPrimeFunctionCall.class);
    FunctionCallFactory factory;

    @BeforeEach
    public void setUpOnce() {
        when(isPrime.getFunctionName()).thenReturn(IsPrimeFunctionCall.FUNCTION_NAME);
        when(nextPrime.getFunctionName()).thenReturn(NextPrimeFunctionCall.FUNCTION_NAME);
        factory = new SimpleFunctionCallFactory(Arrays.asList(isPrime, nextPrime));
    }

    @Test
    public void testCreateIsPrime() {
        assertEquals(isPrime, factory.create(isPrime.getFunctionName()));
    }

    @Test
    public void testCreateNextPrime() {
        assertEquals(nextPrime, factory.create(nextPrime.getFunctionName()));
    }

    @Test
    public void testCreateUnknownFunction() {
        assertThrows(UnknownFunctionException.class, () -> factory.create("sqrt"));
    }
}