package com.dreamlab.nexplorer.api.impl;

import com.dreamlab.nexplorer.api.FunctionCall;
import com.dreamlab.nexplorer.api.FunctionCallFactory;
import com.dreamlab.nexplorer.common.Constants;
import com.dreamlab.nexplorer.exception.UnknownFunctionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SimpleFunctionCallExecutorTest {

    FunctionCall isPrime = mock(IsPrimeFunctionCall.class);
    FunctionCall nextPrime = mock(NextPrimeFunctionCall.class);
    SimpleFunctionCallExecutor functionCallExecutor;

    @BeforeEach
    public void setUpOnce() {
        when(isPrime.getFunctionName()).thenReturn(IsPrimeFunctionCall.FUNCTION_NAME);
        when(nextPrime.getFunctionName()).thenReturn(NextPrimeFunctionCall.FUNCTION_NAME);
        FunctionCallFactory factory = new SimpleFunctionCallFactory(Arrays.asList(isPrime, nextPrime));
        functionCallExecutor = new SimpleFunctionCallExecutor(factory);
    }

    @Test
    public void testIsPrimeFunctionCallReturnsTrue() {
        List<String> args = List.of("871628376123263");
        when(isPrime.execute(args)).thenReturn(Constants.TRUE_STRING);

        assertEquals(Constants.TRUE_STRING, functionCallExecutor.execute(isPrime.getFunctionName(), args));

        verify(isPrime, times(1)).execute(args);
    }

    @Test
    public void testIsPrimeFunctionCallReturnsFalse() {
        List<String> args = List.of("871628376123263");
        when(isPrime.execute(args)).thenReturn(Constants.FALSE_STRING);

        assertEquals(Constants.FALSE_STRING, functionCallExecutor.execute(isPrime.getFunctionName(), args));

        verify(isPrime, times(1)).execute(args);
    }

    @Test
    public void testUnknownFunctionCall() {
        List<String> args = List.of("871628376123263");
        var e = assertThrows(UnknownFunctionException.class, () -> functionCallExecutor.execute("sqrt", args));
        assertEquals("Call to unknown function 'sqrt'", e.getMessage());

        verify(isPrime, never()).execute(args);
    }
}