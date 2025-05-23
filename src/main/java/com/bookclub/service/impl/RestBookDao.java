/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestBookDao implements BookDao {


    /**\
     * Default Constructor
     */
    public RestBookDao() {

    }

    /**
     * Returns book information from external api
     * @param isbnString
     * @return
     */
    public Object getBooksDoc(String isbnString) {
        RestTemplate rest = new RestTemplate();
        String openLibraryUrl = "https://openlibrary.org/api/books";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                .queryParam("bibkeys", isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "details");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = rest.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        String jsonBooklist = response.getBody();

        return Configuration.defaultConfiguration().jsonProvider().parse(jsonBooklist);

    }

    /**
     * Returns book list
     * @return this book list
     */
    @Override
    public List<Book> list(String key) {
        Object doc = getBooksDoc(key);

        List<Book> books = new ArrayList<Book>();

        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");

        for (int index = 0; index < titles.size(); index++) {
            books.add(new Book(isbns.get(index), titles.get(index), infoUrls.get(index)));
        }

        return books;
    }

    /**
     * Finds a book by ISBN
     * @param key
     * @return Book
     */
    @Override
    public Book find(String key) {
        Object doc = getBooksDoc(key);

        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> subtitle = JsonPath.read(doc, "$..details.subtitle");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");
        List<Integer> pages = JsonPath.read(doc, "$..details.number_of_pages");

        String isbn = isbns.size() > 0 ? isbns.get(0) : "N/A";
        String title = titles.size() > 0 ? titles.get(0) : "N/A";
        String desc = subtitle.size() > 0 ? subtitle.get(0) : "N/A";
        String infoUrl = infoUrls.size() > 0 ? infoUrls.get(0) : "N/A";
        int numOfPages = pages.size() > 0 ? pages.get(0) : 0;

        Book book = new Book(isbn, title, desc, infoUrl, numOfPages);

        return book;
    }
}
