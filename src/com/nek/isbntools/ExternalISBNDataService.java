package com.nek.isbntools;

public interface ExternalISBNDataService {
    Book lookUp(String isbn);
}
