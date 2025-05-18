/*
* References
*
* Digital Ocean (2022, August 3). Mockito mock examples. Retrieved May 3, 2025, from https://www.digitalocean.com/community/tutorials/mockito-mock-examples
*
* [Java Techie]. (2018, December 1). Spring Boot Testing | Writing JUnit Tests using JUnit and Mockito | Java Techie [Video]. Youtube. https://www.youtube.com/watch?v=kXhYu939_5s
*
 * Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */

package com.bookclub.javatest;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.impl.MongoWishlistDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoWishlistDaoTest {

    // Mocks
    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private MongoWishlistDao mongoWishlistDao;

    /**
     * Tests MongoWishlistDao find method, validates book information is correct
     */
    @Test
    public void testFindBook() {
        String testId = "testId";
        String isbn = "isbn";
        String title = "title";
        String username = "username";
        String id = "id";

        WishlistItem wishlistItem = new WishlistItem(isbn, title);
        wishlistItem.setUsername(username);
        wishlistItem.setId(id);

        Mockito.when(mongoTemplate.findById(testId, WishlistItem.class)).thenReturn(wishlistItem);

        WishlistItem testWishlistItem = mongoWishlistDao.find(testId);

        assertEquals(testWishlistItem.getId(), id);
        assertEquals(testWishlistItem.getIsbn(), isbn);
        assertEquals(testWishlistItem.getTitle(), title);
        assertEquals(testWishlistItem.getUsername(), username);
    }

    /**
     * Tests MongoWishlistDao list method, validates information is correct on two books
     */
    @Test
    public void testListBooks() {
        String username = "username";

        String isbn1 = "isbn";
        String title1 = "title";
        String id1 = "id";

        String isbn2 = "isbn";
        String title2 = "title";
        String id2 = "id";

        WishlistItem wishlistItem1 = new WishlistItem(isbn1, title1);
        wishlistItem1.setUsername(username);
        wishlistItem1.setId(id1);

        WishlistItem wishlistItem2 = new WishlistItem(isbn2, title2);
        wishlistItem2.setUsername(username);
        wishlistItem2.setId(id2);

        List<WishlistItem> wishlistItemList = new ArrayList<>();
        wishlistItemList.add(wishlistItem1);
        wishlistItemList.add(wishlistItem2);

        Mockito.when(mongoTemplate.find(any(), Mockito.eq(WishlistItem.class))).thenReturn(wishlistItemList);

        List<WishlistItem> testList = mongoWishlistDao.list(username);

        assertEquals(testList.size(), 2);

        assertEquals(testList.get(0).getId(), id1);
        assertEquals(testList.get(0).getIsbn(), isbn1);
        assertEquals(testList.get(0).getTitle(), title1);
        assertEquals(testList.get(0).getUsername(), username);

        assertEquals(testList.get(1).getId(), id2);
        assertEquals(testList.get(1).getIsbn(), isbn2);
        assertEquals(testList.get(1).getTitle(), title2);
        assertEquals(testList.get(1).getUsername(), username);
    }

    /**
     * Tests MongoWishlistDao add method, verifies save is called on the template
     */
    @Test
    public void testAdd() {
        WishlistItem mockWishlistItem = new WishlistItem();
        mongoWishlistDao.add(mockWishlistItem);
        Mockito.verify(mongoTemplate, Mockito.times(1)).save(mockWishlistItem);
    }

    /**
     * Tests MongoWishlistDao remove method, verifies remove is called on the template
     */
    @Test
    public void testRemove() {
        String key = "key";
        mongoWishlistDao.remove(key);
        Mockito.verify(mongoTemplate, Mockito.times(1)).remove(any(), Mockito.eq(WishlistItem.class));
    }
}
