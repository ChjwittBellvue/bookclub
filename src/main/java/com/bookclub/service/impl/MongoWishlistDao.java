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
import org.springframework.data.mongodb.core.query.Criteria;
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
    public List<WishlistItem> list(String username) {
        Query query = new Query();

        query.addCriteria(Criteria.where("username").is(username));

        return mongoTemplate.find(query, WishlistItem.class);
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
        WishlistItem wishlistItem = mongoTemplate.findById(entity.getId(), WishlistItem.class);

        if (wishlistItem != null) {
            wishlistItem.setIsbn(entity.getIsbn());
            wishlistItem.setTitle(entity.getTitle());
            wishlistItem.setUsername(entity.getUsername());

            mongoTemplate.save(wishlistItem);
        }
    }

    /**
     * Removes a wishlist item
     * @param key
     * @return boolean
     */
    @Override
    public boolean remove(String key) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(key));

        mongoTemplate.remove(query, WishlistItem.class);

        return true;
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
