/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.model;

import jakarta.validation.constraints.NotEmpty;
import org.jetbrains.annotations.NotNull;

public class WishlistItem {
    // Attributes
    @NotNull
    @NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    @NotNull
    @NotEmpty(message = "Title is a required field.")
    private String title;

    // Constructors
    public WishlistItem() {
        isbn = "";
        title = "";
    }

    public WishlistItem(@NotNull String isbn, @NotNull String title) {
        this.isbn = isbn;
        this.title = title;
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

    // toString
    @Override
    public String toString() {
        return "Book{isbn=" + isbn + ", " +
                "title=" + title;
    }
}
