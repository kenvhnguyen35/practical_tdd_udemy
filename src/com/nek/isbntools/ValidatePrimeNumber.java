package com.nek.isbntools;

public class ValidatePrimeNumber {
    public boolean isItPrime(int number) {
        int maxDivisor = (int) Math.sqrt(number);
        for (int i = 2; i <= maxDivisor; i++) {
            if (number % i == 0) {
                System.out.println(" found " + i + " -> non prime");
                return false;
            }
        }
        return true;
    }
}
