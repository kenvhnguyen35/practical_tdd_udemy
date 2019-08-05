package com.nek.isbntools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateISBNTest {

    @Test
    public void checkValidISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue("first value to test", result);
        result = validator.checkISBN("0140177396");
        assertTrue("second value to test", result);
    }

    @Test
    public void checkAnInvalidISBN() {
        ValidateISBN validatorISBN = new ValidateISBN();
        boolean result = validatorISBN.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void ninedigitsIsbnAreNotAllowed() {
        ValidateISBN validatorISBN = new ValidateISBN();
        validatorISBN.checkISBN("123456789");
    }

    @Test(expected = NumberFormatException.class)
    public void nonNumericIsbnAreNotAllowed() {
        ValidateISBN validatorISBN = new ValidateISBN();
        validatorISBN.checkISBN("helloworld");
    }

    @Test
    public void IsbnNumbersEndingInAnXAreValid() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }
}