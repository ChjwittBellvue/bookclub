/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.service.impl;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * List Wishlist Items
     * @return List of wishlist items
     */
    @Override
    public List<WishlistItem> list() {
        return mongoTemplate.findAll(WishlistItem.class);
    }

    /**
     * Adds a new wishlist item
     * @param entity
     */
    @Override
    public void add(WishlistItem entity) {
        mongoTemplate.save(entity);
    }

    /**
     * Updates a current wishlist item
     * @param entity
     */
    @Override
    public void update(WishlistItem entity) {

    }

    /**
     * Removes a wishlist item
     * @param entity
     * @return boolean
     */
    @Override
    public boolean remove(WishlistItem entity) {
        return false;
    }

    /**
     * Finds a wishlist item by its id
     * @param key
     * @return WishlistItem
     */
    @Override
    public WishlistItem find(String key) {
        return mongoTemplate.findById(key, WishlistItem.class);
    }
}
