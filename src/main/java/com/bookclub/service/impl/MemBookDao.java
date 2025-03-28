/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

import java.util.ArrayList;
import java.util.List;

public class MemBookDao implements BookDao {
    private List<Book> books = new ArrayList<Book>();

    /**\
     * Default Constructor - adds 5 books
     */
    public MemBookDao() {
        List<String> hitchhikersGuideAuthors = new ArrayList<>();
        hitchhikersGuideAuthors.add("Douglas Adams");
        Book hitchhikersGuide = new Book("1",
                "Hitchhiker's Guide to the Galaxy",
                "A space comedy.",
                208,
                hitchhikersGuideAuthors
                );

        List<String> reaperManAuthors = new ArrayList<>();
        reaperManAuthors.add("Terry Pratchett");
        Book reaperMan = new Book("2",
                "Reaper Man",
                "A comedy about death personified.",
                208,
                reaperManAuthors
        );

        List<String> goodOmensAuthors = new ArrayList<>();
        goodOmensAuthors.add("Terry Pratchett");
        goodOmensAuthors.add("Neil Gaiman");
        Book goodOmens = new Book("3",
                "Good Omens",
                "A comedy about the apocalypse.",
                400,
                goodOmensAuthors
        );

        List<String> endersGameAuthors = new ArrayList<>();
        endersGameAuthors.add("Orson Scott Card");
        Book endersGame = new Book("4",
                "Ender's Game",
                "A sci-fi struggle for humanity's survival.",
                256,
                endersGameAuthors
        );

        List<String> spellmongerAuthors = new ArrayList<>();
        spellmongerAuthors.add("Terry Mancour");
        Book spellmonger = new Book("5",
                "Spellmonger",
                "A fantasy tale.",
                624,
                spellmongerAuthors
        );
        books.add(hitchhikersGuide);
        books.add(reaperMan);
        books.add(goodOmens);
        books.add(endersGame);
        books.add(spellmonger);
    }

    /**
     * Returns book list
     * @return this book list
     */
    @Override
    public List<Book> list() {
        return this.books;
    }

    /**
     * Finds a book by ISBN
     * @param key
     * @return Book
     */
    @Override
    public Book find(String key) {
        for (Book book : this.books) {
            if (book.getIsbn().equals(key)) {
                return book;
            }
        }
        return new Book();
    }
}
