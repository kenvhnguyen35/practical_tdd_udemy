package com.nek.isbntools;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class StockManagementTests {

    ExternalISBNDataService fakeWebService;
    ExternalISBNDataService fakeDatabaseService;
    StockManager stockManager;
    String isbn = "0140177396";

    @Before
    public void setup() {
        System.out.println("setup running before every test, refresh setup objects...");
        fakeWebService = mock(ExternalISBNDataService.class);
        fakeDatabaseService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setExternalWebService(fakeWebService);
        stockManager.setDatabaseService(fakeDatabaseService);
    }

    @Test
    public void testCanGetACorrectLocatorCode() {

        when(fakeDatabaseService.lookUp(anyString())).thenReturn(null);
        when(fakeWebService.lookUp(isbn)).thenReturn(new Book(isbn, "Of Mice And Men", "J. Steinbeck"));

        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {

        when(fakeDatabaseService.lookUp(isbn)).thenReturn(new Book(isbn, "blah", "bleh"));

        stockManager.getLocatorCode(isbn);

        verify(fakeDatabaseService).lookUp(isbn);
        verify(fakeWebService, never()).lookUp(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {

        when(fakeDatabaseService.lookUp(anyString())).thenReturn(null); // this is a mock, but it is also a stub
        when(fakeWebService.lookUp(isbn)).thenReturn(new Book(isbn, "blah", "bleh")); // test only behavior, care not about logic!

        stockManager.getLocatorCode(isbn);

        verify(fakeDatabaseService).lookUp(isbn); // verify instead of assert
        verify(fakeWebService).lookUp(isbn);
    }
}
