package com.martynovich.hotel.dao;

import com.martynovich.hotel.domain.Apartment;

import java.util.List;

/**
 * @author
 */
public interface IApartmentDao {

    void insert(Apartment apartment);

    Apartment read(long id);

    void update(Apartment apartment);

    void remove(long id);

    List<Apartment> readAll();
}
