package com.dreamlab.nexplorer.api.impl;

import com.dreamlab.nexplorer.api.FunctionCallFactory;
import com.dreamlab.nexplorer.exception.UnknownFunctionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SimpleFunctionCallFactoryTest {

    IsPrimeFunctionCall isPrime = mock(IsPrimeFunctionCall.class);
    FunctionCallFactory factory = new SimpleFunctionCallFactory(isPrime);

    @Test
    public void testCreateIsPrime() {
        assertEquals(isPrime, factory.create(IsPrimeFunctionCall.FUNCTION_CALL));
    }

    @Test
    public void testCreateUnknownFunction() {
        assertThrows(UnknownFunctionException.class, () -> factory.create("sqrt"));
    }
}