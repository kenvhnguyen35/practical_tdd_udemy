package com.nek.isbntools;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class StockManagementTests {
    @Test
    public void testCanGetACorrectLocatorCode() {
        ExternalISBNDataService fakeService = (isbn) -> new Book(isbn, "Of Mice And Men", "J. Steinbeck"); // This is a stub

        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setExternalService(fakeService);
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }
}
