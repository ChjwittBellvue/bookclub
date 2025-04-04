/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.web;
import com.bookclub.model.WishlistItem;
import com.bookclub.service.impl.MemWishlistDao;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    /**
     * Moves user to wishlist page
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showWishlist(Model model)
    {
        MemWishlistDao memWishlistDao = new MemWishlistDao();
        List<WishlistItem> wishlist = memWishlistDao.list();

        model.addAttribute("wishlist", wishlist);
        return "wishlist/list";
    } // end showWishlist

    /**
     * Moves user to new item (wishlist) page
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/new")
    public String wishlistForm(Model model)
    {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    } // end wishlistForm

    /**
     * Adds WishListItem object, checks for errors and redirects if it finds any
     * @param wishlistItem
     * @param bindingResult
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult)
    {
        System.out.println("mywishlist: " + wishlistItem.toString());
        System.out.println(bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }
        return "redirect:/wishlist";
    } // end addWishlistItem
}
