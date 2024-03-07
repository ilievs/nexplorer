package com.dreamlab.nexplorer.api.impl;

import com.dreamlab.nexplorer.api.FunctionCall;
import com.dreamlab.nexplorer.exception.IncorrectArgumentFormatException;
import com.dreamlab.nexplorer.exception.WrongNumberOfArgumentsException;
import com.dreamlab.nexplorer.service.PrimeNumberService;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class NextPrimeFunctionCallTest {

    PrimeNumberService primeFinder = mock(PrimeNumberService.class);
    FunctionCall functionCall = new NextPrimeFunctionCall(primeFinder);

    @Test
    public void testNextPrimeWithValidNumber() {
        BigInteger arg = BigInteger.valueOf(827637126378L);
        when(primeFinder.nextPrime(arg)).thenReturn(arg);

        List<String> args = List.of(arg.toString());
        assertEquals(arg.toString(), functionCall.execute(args));

        verify(primeFinder, times(1)).nextPrime(arg);
    }

    @Test
    public void testAlphabetCharactersArguments() {
        var arg = "asdxzcbasdgweq";
        List<String> args = List.of(arg);

        var e = assertThrows(IncorrectArgumentFormatException.class, () -> functionCall.execute(args));

        assertEquals("Incorrect format for argument 'asdxzcbasdgweq'" +
                " at position 1 for function call to 'nextPrime'", e.getMessage());

        verify(primeFinder, never()).nextPrime(any());
    }

    @Test
    public void testSpecialCharactersArguments() {
        var arg = "*&!@*&*$@&";
        List<String> args = List.of(arg);

        var e = assertThrows(IncorrectArgumentFormatException.class, () -> functionCall.execute(args));

        assertEquals("Incorrect format for argument '*&!@*&*$@&'" +
                " at position 1 for function call to 'nextPrime'", e.getMessage());

        verify(primeFinder, never()).nextPrime(any());
    }

    @Test
    public void testAlphanumericCharactersArguments() {
        var arg = "as342dxas43432g2weq";
        List<String> args = List.of(arg);

        var e = assertThrows(IncorrectArgumentFormatException.class, () -> functionCall.execute(args));

        assertEquals("Incorrect format for argument 'as342dxas43432g2weq'" +
                " at position 1 for function call to 'nextPrime'", e.getMessage());

        verify(primeFinder, never()).nextPrime(any());
    }

    @Test
    public void testAlphanumericAndSpecialCharactersArguments() {
        var arg = "kjas7623^%112lksd98(";
        List<String> args = List.of(arg);

        var e = assertThrows(IncorrectArgumentFormatException.class, () -> functionCall.execute(args));

        assertEquals("Incorrect format for argument 'kjas7623^%112lksd98('" +
                " at position 1 for function call to 'nextPrime'", e.getMessage());

        verify(primeFinder, never()).nextPrime(any());
    }

    @Test
    public void testZeroArguments() {
        var e = assertThrows(WrongNumberOfArgumentsException.class,
                () -> functionCall.execute(List.of()));

        assertEquals("Wrong number of arguments for function call to 'nextPrime'." +
                " Expected 1 arguments, got 0 ()", e.getMessage());

        verify(primeFinder, never()).nextPrime(any());
    }

    @Test
    public void testMoreThanOneArguments() {
        var e = assertThrows(WrongNumberOfArgumentsException.class,
                () -> functionCall.execute(List.of()));

        assertEquals("Wrong number of arguments for function call to 'nextPrime'." +
                " Expected 1 arguments, got 0 ()", e.getMessage());

        verify(primeFinder, never()).nextPrime(any());
    }
}