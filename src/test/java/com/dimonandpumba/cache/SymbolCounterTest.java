package com.dimonandpumba.cache;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

class SymbolCounterTest {

    SymbolCounter symbolCounter = new SymbolCounter();

    @Test
    void findUnigueCharacters() {
        LinkedHashMap<Character,Integer> expected = symbolCounter.findUnigueCharacters("hello");
        LinkedHashMap<Character,Integer> actual = new LinkedHashMap<>();
        actual.put('h', 1);
        actual.put('e', 1);
        actual.put('l', 2);
        actual.put('o', 1);
        assertEquals(expected,actual);
    }

    @Test
    void findSpacesCharacters() {
        LinkedHashMap<Character,Integer> expected = symbolCounter.findUnigueCharacters("     ");
        LinkedHashMap<Character,Integer> actual = new LinkedHashMap<>();
        actual.put(' ', 5);
        assertEquals(expected,actual);
    }

    @Test
    void  trueRecordInMemory() {
        symbolCounter.countingSymbol("I am");
        symbolCounter.countingSymbol("I am");
        symbolCounter.countingSymbol("checking");
        symbolCounter.countingSymbol("checking");
        symbolCounter.countingSymbol("the");
        symbolCounter.countingSymbol("the");
        symbolCounter.countingSymbol("program");
        symbolCounter.countingSymbol("for");
        symbolCounter.countingSymbol("for");
        symbolCounter.countingSymbol("correctness");
        symbolCounter.countingSymbol("correctness");

        assertNotNull(symbolCounter.getCacheEntry("I am"));
    }

    @Test
    void  falseRecordInMemory() {
        symbolCounter.countingSymbol("I am");
        symbolCounter.countingSymbol("I am");
        symbolCounter.countingSymbol("checking");
        symbolCounter.countingSymbol("checking");
        symbolCounter.countingSymbol("the");
        symbolCounter.countingSymbol("the");
        symbolCounter.countingSymbol("program");
        symbolCounter.countingSymbol("for");
        symbolCounter.countingSymbol("for");
        symbolCounter.countingSymbol("correctness");
        symbolCounter.countingSymbol("correctness");

        assertNull(symbolCounter.getCacheEntry("program"));
    }

}
