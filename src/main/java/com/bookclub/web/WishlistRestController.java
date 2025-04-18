/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.service.impl.MongoWishlistDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
public class WishlistRestController {

    @Autowired
    WishlistDao wishlistDao = new MongoWishlistDao();

    public void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    @GetMapping()
    public List<WishlistItem> showWishlist(Authentication authentication) {
        String username = authentication.getName();
        return wishlistDao.list(username);
    }

//    @GetMapping(path = "/{id}")
//    public WishlistItem findById(@PathVariable String id) {
//        return wishlistDao.find(id);
//    }

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

    @RequestMapping(method = RequestMethod.DELETE, path= "/{id}")
    public String removeWishlistItem(@PathVariable String id, Authentication authentication) {

        wishlistDao.remove(id);

        return "wishlist/view";
    }
}
