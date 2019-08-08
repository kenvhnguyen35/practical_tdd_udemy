package com.nek.isbntools;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ValidatePrimeNumberTest {

    @Test
    public void testPrimeNumber() {
        Integer numbers[] = {1, 2, 13, 23, 61, 79};
        for (Integer primeNumber : Arrays.asList(numbers)) {
            assertTrue( new ValidatePrimeNumber().isItPrime(primeNumber));
        }
    }

    @Test
    public void testNonPrimeNumber() {
        Integer numbers[] = {15, 25, 60, 63, 207};
        for (Integer nonPrimeNumber : Arrays.asList(numbers)) {
            System.out.println("test " + nonPrimeNumber);
            assertFalse("test " + nonPrimeNumber, new ValidatePrimeNumber().isItPrime(nonPrimeNumber));
        }
    }
}