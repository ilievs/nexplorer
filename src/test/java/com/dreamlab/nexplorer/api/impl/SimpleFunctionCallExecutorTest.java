package com.dreamlab.nexplorer.api.impl;

import com.dreamlab.nexplorer.api.FunctionCallFactory;
import com.dreamlab.nexplorer.api.FunctionCallExecutor;
import com.dreamlab.nexplorer.common.Constants;
import com.dreamlab.nexplorer.exception.UnknownFunctionException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SimpleFunctionCallExecutorTest {

    IsPrimeFunctionCall isPrimeCall = mock(IsPrimeFunctionCall.class);
    NextPrimeFunctionCall nextPrime = mock(NextPrimeFunctionCall.class);
    FunctionCallFactory factory = new SimpleFunctionCallFactory(isPrimeCall, nextPrime);
    FunctionCallExecutor functionCallExecutor = new SimpleFunctionCallExecutor(factory);

    @Test
    public void testIsPrimeFunctionCallReturnsTrue() {
        List<String> args = Collections.singletonList("871628376123263");
        when(isPrimeCall.execute(args)).thenReturn(Constants.TRUE_STRING);

        assertEquals(Constants.TRUE_STRING,
                functionCallExecutor.execute(IsPrimeFunctionCall.FUNCTION_CALL, args));

        verify(isPrimeCall, times(1)).execute(args);
    }

    @Test
    public void testIsPrimeFunctionCallReturnsFalse() {
        List<String> args = Collections.singletonList("871628376123263");
        when(isPrimeCall.execute(args)).thenReturn(Constants.FALSE_STRING);

        assertEquals(Constants.FALSE_STRING,
                functionCallExecutor.execute(IsPrimeFunctionCall.FUNCTION_CALL, args));

        verify(isPrimeCall, times(1)).execute(args);
    }

    @Test
    public void testUnknownFunctionCall() {
        List<String> args = Collections.singletonList("871628376123263");
        var e = assertThrows(UnknownFunctionException.class, () -> functionCallExecutor.execute("sqrt", args));
        assertEquals("Call to unknown function 'sqrt'", e.getMessage());

        verify(isPrimeCall, never()).execute(args);
    }
}