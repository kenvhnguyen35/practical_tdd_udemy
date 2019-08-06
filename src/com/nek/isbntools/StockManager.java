package com.nek.isbntools;

public class StockManager {

    private ExternalISBNDataService externalWebService;
    private ExternalISBNDataService databaseService;

    public void setExternalWebService(ExternalISBNDataService externalWebService) {
        this.externalWebService = externalWebService;
    }

    public void setDatabaseService(ExternalISBNDataService databaseService) {
        this.databaseService = databaseService;
    }

    public String getLocatorCode(String isbn) {
        Book book = databaseService.lookUp(isbn);
        if (null == book) {
            book = externalWebService.lookUp(isbn);
        }
        StringBuilder locator = new StringBuilder();
        locator.append(book.getIsbn().substring(isbn.length() - 4));
        locator.append(book.getAuthor(), 0, 1);
        locator.append(book.getTitle().split(" ").length);
        return locator.toString();
    }
}
