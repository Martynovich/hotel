package com.martynovich.hotel.dao;

import com.martynovich.hotel.domain.Order;

import java.util.List;

/**
 * @author
 */
public interface IOrderDao {
    void insert(Order order);

    Order read(long id);

    void update(Order order);

    void remove(long id);

    List<Order> readAll();
}
