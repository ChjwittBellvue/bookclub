/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.service.impl;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

import java.util.ArrayList;
import java.util.List;

public class MemWishlistDao implements WishlistDao {

    private List<WishlistItem> wishlist = new ArrayList<>();

    public MemWishlistDao() {
        WishlistItem wishlistItem = new WishlistItem("1", "Hitchhiker's Guide to the Galaxy");
        wishlist.add(wishlistItem);
    }

    /**
     * Returns WishlistItem list
     * @return this book list
     */
    @Override
    public List<WishlistItem> list() {
        return this.wishlist;
    }

    /**
     * Finds a WishlistItem by ISBN
     * @param key
     * @return WishlistItem
     */
    @Override
    public WishlistItem find(String key) {
        for (WishlistItem wishlistItem : this.wishlist) {
            if (wishlistItem.getIsbn().equals(key)) {
                return wishlistItem;
            }
        }
        return new WishlistItem();
    }
}
