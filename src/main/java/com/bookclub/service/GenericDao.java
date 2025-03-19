/*
 *  REFERENCES
 *
 *  Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */
package com.bookclub.service;

import java.util.List;

public interface GenericDao<E, K> {
    List<E> list();  // Return a list of objects of type E.
    E find(K key); // Return an object of type E by type K value.
}
