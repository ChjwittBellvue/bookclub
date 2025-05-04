/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.web;
import com.bookclub.model.WishlistItem;
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
import com.bookclub.service.dao.WishlistDao;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    WishlistDao wishlistDao = new MongoWishlistDao();

    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    /**
     * Moves user to wishlist page
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showWishlist(Model model)
    {
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
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication)
    {
        wishlistItem.setUsername(authentication.getName());
        System.out.println(bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        wishlistDao.add(wishlistItem);

        return "redirect:/wishlist";
    } // end addWishlistItem

    @RequestMapping(method = RequestMethod.GET, path= "/{id}")
    public String showWishlistItem(@PathVariable String id, Model model) {
        WishlistItem wishlistItem = wishlistDao.find(id);

        model.addAttribute("wishlistItem", wishlistItem);

        return "wishlist/view";
    }

    @RequestMapping(method = RequestMethod.POST, path= "/update")
    public String updateWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication) {
        wishlistItem.setUsername(authentication.getName());

        if (bindingResult.hasErrors()) {
            return "wishlist/view";
        }

        wishlistDao.update(wishlistItem);

        return "redirect:/wishlist";
    }

    @RequestMapping(method = RequestMethod.GET, path= "/remove/{id}")
    public String removeWishlistItem(@PathVariable String id, Authentication authentication) {

        wishlistDao.remove(id);

        return "wishlist/list";
    }
}
