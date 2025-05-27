/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.web;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.service.impl.MongoBookOfTheMonthDao;
import com.bookclub.service.impl.MongoWishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/monthly-books", produces = "application/json")
@CrossOrigin(origins = "*")
public class AdminRestController {

    @Autowired
    BookOfTheMonthDao bookOfTheMonthDao = new MongoBookOfTheMonthDao();

    public void setWishlistDao(BookOfTheMonthDao bookOfTheMonthDao) {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }

    /**
     * Returns all books of the month
     * @return
     */
    @GetMapping()
    public List<BookOfTheMonth> showBooksOfTheMonth() {
        return bookOfTheMonthDao.list("999");
    }

    /**
     * Finds book of the month by id
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public BookOfTheMonth findById(@PathVariable String id) {
        return bookOfTheMonthDao.find(id);
    }

}
