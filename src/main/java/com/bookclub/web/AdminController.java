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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/monthly-books")
public class AdminController {

    BookOfTheMonthDao bookOfTheMonthDao = new MongoBookOfTheMonthDao();

    @Autowired
    private void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao) {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }

    /**
     * Moves user to book of the month page
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showBookOfTheMonth(Model model)
    {
        model.addAttribute("books", bookOfTheMonthDao.list("999"));
        return "monthly-books/list";
    } // end showWishlist

    /**
     * Moves user to new book of the month page
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/new")
    public String bookOfTheMonthForm(Model model)
    {
        model.addAttribute("bookOfTheMonth", new BookOfTheMonth());
        model.addAttribute("months", getMonths());
        return "monthly-books/new";
    } // end wishlistForm

    /**
     * Adds BookOfTheMonth object, checks for errors and redirects if it finds any
     * @param bookOfTheMonth
     * @param bindingResult
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addBookOfTheMonth(@Valid BookOfTheMonth bookOfTheMonth, BindingResult bindingResult, Model model)
    {
        System.out.println(bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "monthly-books/new";
        }

        bookOfTheMonthDao.add(bookOfTheMonth);

        return "redirect:/monthly-books";
    } // end addWishlistItem

    /**
     * Shows book of the month
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path= "/{id}")
    public String showBookOfTheMonth(@PathVariable String id, Model model) {
        BookOfTheMonth bookOfTheMonth = bookOfTheMonthDao.find(id);

        model.addAttribute("bookOfTheMonth", bookOfTheMonth);

        return "monthly-books/view";
    }

    /**
     * Updates book of the month
     * @param bookOfTheMonth
     * @param bindingResult
     * @param authentication
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path= "/update")
    public String updateBookOfTheMonth(@Valid BookOfTheMonth bookOfTheMonth, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "monthly-books/view";
        }

        bookOfTheMonthDao.update(bookOfTheMonth);

        return "redirect:/monthly-books";
    }

    /**
     * Removes book of the month
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path= "/remove/{id}")
    public String removeBookOfTheMonth(@PathVariable String id) {

        bookOfTheMonthDao.remove(id);

        return "monthly-books/list";
    }

    /**
     * Maps months to number of month in the year
     * @return
     */
    private Map<Integer, String> getMonths() {
        Map<Integer, String> months = new HashMap<>();
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");
        return months;
    }
}
