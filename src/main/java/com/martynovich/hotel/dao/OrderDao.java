package com.martynovich.hotel.dao;

import com.martynovich.hotel.domain.Order;
import com.martynovich.hotel.util.ConnectionPoint;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author
 */
public class OrderDao implements IOrderDao {
    final static Logger LOGGER = Logger.getLogger(OrderDao.class);
    private ConnectionPoint connectionPoint;
    @Override
    public void insert(Order order) {

    }

    @Override
    public Order read(long id) {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void remove(long id) {

    }

    @Override
    public List<Order> readAll() {
        return null;
    }
}
