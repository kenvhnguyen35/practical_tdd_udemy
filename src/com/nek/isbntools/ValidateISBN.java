package com.nek.isbntools;

public class ValidateISBN {
    public boolean checkISBN(String isbn) {
        if (isbn.length() != 10 && isbn.length() != 13) throw new NumberFormatException("ISBN numbers must be 10 or 13 digits");
        //if (isbn.matches("^[a-zA-Z]*$")) throw new NumberFormatException("ISBN numbers must contains only digits");
        int total = 0;
        if (isbn.length() == 10) {
            for (int i = 0; i < 10; i ++) {
                if (!Character.isDigit(isbn.charAt(i))) {
                    if (i == 9 && isbn.charAt(i) == 'X') {
                        total += 10; // according to wikipedia
                    }
                    else {
                        throw new NumberFormatException("ISBN numbers must contains only digits");
                    }
                } else {
                    total += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
                }
            }
            if (total % 11 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            for (int i = 0; i < 13; i++) {
                if (i % 2 == 1) {
                    total += Character.getNumericValue(isbn.charAt(i)) * 3;
                } else {
                    total += Character.getNumericValue(isbn.charAt(i));
                }
            }
            if (total % 10 == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
