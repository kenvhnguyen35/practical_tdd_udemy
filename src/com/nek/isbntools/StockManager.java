package com.nek.isbntools;

public class StockManager {

    private ExternalISBNDataService externalService;

    public void setExternalService(ExternalISBNDataService externalService) {
        this.externalService = externalService;
    }

    public String getLocatorCode(String isbn) {
        Book book = externalService.lookUp(isbn);
        StringBuilder locator = new StringBuilder();
        locator.append(book.getIsbn().substring(isbn.length() - 4));
        locator.append(book.getAuthor().substring(0,1));
        locator.append(book.getTitle().split(" ").length);
        return locator.toString();
    }
}
