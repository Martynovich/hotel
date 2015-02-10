package com.martynovich.hotel.dao;

import com.martynovich.hotel.domain.Client;

import java.util.List;

/**
 * @author
 */
public interface IClientDao {
    void insert(Client client);

    Client read(String login);

    Client read(long id);

    void update(Client client);

    void remove(long id);

    List<Client> readAll();
}
