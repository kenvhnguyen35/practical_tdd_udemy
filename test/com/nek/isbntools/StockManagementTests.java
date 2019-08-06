package com.nek.isbntools;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class StockManagementTests {
    @Test
    public void testCanGetACorrectLocatorCode() {
        ExternalISBNDataService fakeWebService = (isbn) -> new Book(isbn, "Of Mice And Men", "J. Steinbeck"); // This is a stub
        ExternalISBNDataService fakeDatabaseService = (isbn) -> null;

        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setExternalWebService(fakeWebService);
        stockManager.setDatabaseService(fakeDatabaseService);

        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        ExternalISBNDataService fakeWebService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService fakeDatabaseService = mock(ExternalISBNDataService.class);

        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setExternalWebService(fakeWebService);
        stockManager.setDatabaseService(fakeDatabaseService);

        when(fakeDatabaseService.lookUp(isbn)).thenReturn(new Book(isbn, "blah", "bleh"));

        stockManager.getLocatorCode(isbn);

        verify(fakeDatabaseService).lookUp(isbn);
        verify(fakeWebService, never()).lookUp(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
        ExternalISBNDataService fakeWebService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService fakeDatabaseService = mock(ExternalISBNDataService.class);

        String isbn = "0140177396";
        StockManager stockManager = new StockManager();
        stockManager.setExternalWebService(fakeWebService);
        stockManager.setDatabaseService(fakeDatabaseService);

        when(fakeDatabaseService.lookUp(anyString())).thenReturn(null); // this is a mock, but it is also a stub
        when(fakeWebService.lookUp(isbn)).thenReturn(new Book(isbn, "blah", "bleh")); // test only behavior, care not about logic!

        stockManager.getLocatorCode(isbn);

        verify(fakeDatabaseService).lookUp(isbn); // verify instead of assert
        verify(fakeWebService).lookUp(isbn);
    }
}
