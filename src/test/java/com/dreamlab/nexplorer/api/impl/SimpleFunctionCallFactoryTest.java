package com.dreamlab.nexplorer.api.impl;

import com.dreamlab.nexplorer.api.FunctionCallFactory;
import com.dreamlab.nexplorer.exception.UnknownFunctionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class SimpleFunctionCallFactoryTest {

    IsPrimeFunctionCall isPrime = mock(IsPrimeFunctionCall.class);
    NextPrimeFunctionCall nextPrime = mock(NextPrimeFunctionCall.class);
    FunctionCallFactory factory = new SimpleFunctionCallFactory(isPrime, nextPrime);

    @Test
    public void testCreateIsPrime() {
        assertEquals(isPrime, factory.create(IsPrimeFunctionCall.FUNCTION_CALL));
    }

    @Test
    public void testCreateNextPrime() {
        assertEquals(nextPrime, factory.create(NextPrimeFunctionCall.FUNCTION_CALL));
    }

    @Test
    public void testCreateUnknownFunction() {
        assertThrows(UnknownFunctionException.class, () -> factory.create("sqrt"));
    }
}