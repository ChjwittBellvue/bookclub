/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.service.impl;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(BookOfTheMonth entity) {
        mongoTemplate.save(entity);
    }

    /**
     * Updates an existing book of the month
     * @param entity
     */
    @Override
    public void update(BookOfTheMonth entity) {
        BookOfTheMonth bookOfTheMonth = mongoTemplate.findById(entity.getId(), BookOfTheMonth.class);

        if (bookOfTheMonth != null) {
            bookOfTheMonth.setIsbn(entity.getIsbn());
            bookOfTheMonth.setMonth(entity.getMonth());

            mongoTemplate.save(bookOfTheMonth);
        }
    }

    /**
     * Removes existing book of the month
     * @param key
     * @return
     */
    @Override
    public boolean remove(String key) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(key));

        mongoTemplate.remove(query, BookOfTheMonth.class);

        return true;
    }

    /**
     * Returns full list of books of the month
     * @param key
     * @return
     */
    @Override
    public List<BookOfTheMonth> list(String key) {
        int month = Integer.parseInt(key);

        if (month == 999) {
            return mongoTemplate.findAll(BookOfTheMonth.class);
        }

        Query query = new Query();

        query.addCriteria(Criteria.where("month").is(month));

        return mongoTemplate.find(query, BookOfTheMonth.class);
    }

    /**
     * Returns book of the month by id
     * @param key
     * @return
     */
    @Override
    public BookOfTheMonth find(String key) {
        return mongoTemplate.findById(key, BookOfTheMonth.class);
    }
}
