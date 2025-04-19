/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.model;

import java.util.List;

public class Book {
    // Attributes
    private String isbn;
    private String title;
    private String description;
    private int numOfPages;
    private String infoUrl;

    // Constructors
    public Book() {
    }

    public Book(String isbn, String title, String description, String infoUrl, int numOfPages) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.infoUrl = infoUrl;
        this.numOfPages = numOfPages;

    }

    public Book(String isbn, String title, String infoUrl) {
        this.isbn = isbn;
        this.title = title;
        this.infoUrl = infoUrl;
    }

    // Mutators
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String numOfPages) {
        this.infoUrl = infoUrl;
    }

    // toString
    @Override
    public String toString() {
        return "Book{isbn=" + isbn + ", " +
                "title=" + title + ", " +
                "description=" + description + ", " +
                "infoUrl=" + infoUrl + ", " +
                "numOfPages=" + numOfPages + "}";
    }
}
