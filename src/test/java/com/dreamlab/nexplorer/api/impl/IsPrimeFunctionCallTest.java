package com.dreamlab.nexplorer.api.impl;

import java.math.BigInteger;
import java.util.List;

import com.dreamlab.nexplorer.api.FunctionCall;
import com.dreamlab.nexplorer.common.Constants;
import com.dreamlab.nexplorer.exception.IncorrectArgumentFormatException;
import com.dreamlab.nexplorer.exception.WrongNumberOfArgumentsException;
import com.dreamlab.nexplorer.service.PrimeNumberService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IsPrimeFunctionCallTest {

    PrimeNumberService primeNumberServiceMock = mock(PrimeNumberService.class);
    FunctionCall functionCall = new IsPrimeFunctionCall(primeNumberServiceMock);

    @Test
    public void testCorrectArgumentsReturnTrue() {
        var arg = new BigInteger("87239781928389219873834");
        when(primeNumberServiceMock.isPrime(arg)).thenReturn(true);
        assertEquals(Constants.TRUE_STRING, functionCall.execute(List.of(arg.toString())));
        verify(primeNumberServiceMock, times(1)).isPrime(any());
    }

    @Test
    public void testCorrectArgumentsReturnFalse() {
        var arg = new BigInteger("9182398129389123");
        when(primeNumberServiceMock.isPrime(arg)).thenReturn(false);
        assertEquals(Constants.FALSE_STRING, functionCall.execute(List.of(arg.toString())));
        verify(primeNumberServiceMock, times(1)).isPrime(any());
    }

    @Test
    public void testAlphabetCharactersArguments() {
        var arg = "asdxzcbasdgweq";
        List<String> args = List.of(arg);
        var e = assertThrows(IncorrectArgumentFormatException.class, () -> functionCall.execute(args));

        assertEquals("Incorrect format for argument 'asdxzcbasdgweq'" +
                        " at position 1 for function call to 'isPrime'", e.getMessage());

        verify(primeNumberServiceMock, never()).isPrime(any());
    }

    @Test
    public void testSpecialCharactersArguments() {
        var arg = "*&!@*&*$@&";
        List<String> args = List.of(arg);
        var e = assertThrows(IncorrectArgumentFormatException.class, () -> functionCall.execute(args));

        assertEquals("Incorrect format for argument '*&!@*&*$@&'" +
                        " at position 1 for function call to 'isPrime'", e.getMessage());

        verify(primeNumberServiceMock, never()).isPrime(any());
    }

    @Test
    public void testAlphanumericCharactersArguments() {
        var arg = "as342dxas43432g2weq";
        List<String> args = List.of(arg);
        var e = assertThrows(IncorrectArgumentFormatException.class, () -> functionCall.execute(args));

        assertEquals("Incorrect format for argument 'as342dxas43432g2weq'" +
                        " at position 1 for function call to 'isPrime'", e.getMessage());

        verify(primeNumberServiceMock, never()).isPrime(any());
    }

    @Test
    public void testAlphanumericAndSpecialCharactersArguments() {
        var arg = "kjas7623^%112lksd98(";
        List<String> args = List.of(arg);
        var e = assertThrows(IncorrectArgumentFormatException.class, () -> functionCall.execute(args));

        assertEquals("Incorrect format for argument 'kjas7623^%112lksd98('" +
                        " at position 1 for function call to 'isPrime'", e.getMessage());

        verify(primeNumberServiceMock, never()).isPrime(any());
    }

    @Test
    public void testZeroArguments() {
        var e = assertThrows(WrongNumberOfArgumentsException.class, () -> functionCall.execute(List.of()));
        assertEquals("Wrong number of arguments for function call to 'isPrime'." +
                " Expected 1 arguments, got 0 ()", e.getMessage());
        verify(primeNumberServiceMock, never()).isPrime(any());
    }

    @Test
    public void testMoreThanOneArgument() {
        var e = assertThrows(WrongNumberOfArgumentsException.class, () -> functionCall.execute(List.of()));
        assertEquals("Wrong number of arguments for function call to 'isPrime'." +
                " Expected 1 arguments, got 0 ()", e.getMessage());
        verify(primeNumberServiceMock, never()).isPrime(any());
    }
}