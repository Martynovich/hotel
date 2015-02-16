package com.martynovich.hotel.dao;

import com.martynovich.hotel.domain.Client;
import com.martynovich.hotel.domain.Order;
import com.martynovich.hotel.util.ConnectionPoint;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class OrderDao implements IOrderDao {
    final static Logger LOGGER = Logger.getLogger(OrderDao.class);
    private ConnectionPoint connectionPoint;
    private  static final String INSERT_QUERY = "INSERT INTO orders " +
            "(client_id_fk, apartment_id_fk, start_date, end_date) VALUES (?, ?, ?, ?)";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM hotel.orders WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE hotel.orders " +
                    "SET client_id_fk = ?, apartment_id_fk = ?, start_date = ?, end_date = ?" +
                    "WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM hotel.orders WHERE id = ?";
    private static final String READ_ALL_QUERY = "SELECT * FROM hotel.orders";

    private OrderDao(){
        this.connectionPoint = ConnectionPoint.getInstance();
    }

    @Override
    public void insert(Order order) {
        LOGGER.info("Order inserting started");
        try (Connection connection = connectionPoint.getConnection()) {
            connection.prepareStatement(INSERT_QUERY);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setLong(1, order.getClient().getId());
            preparedStatement.setLong(2, order.getApartment().getId());
            preparedStatement.setDate(3, (Date) order.getStartDate());
            preparedStatement.setDate(4, (Date) order.getEndDate());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with order inserting", e);
        }
    }

    @Override
    public Order read(long id) {
        Order order = new Order();
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                order.setId(resultSet.getInt("id"));
                order.setClient(new ClientDao().read(resultSet.getLong("client_id_fk")));
                order.setApartment(new ApartmentDao().read(resultSet.getLong("apartment_id_fk")));
                order.setStartDate(resultSet.getDate("start_date"));
                order.setEndDate(resultSet.getDate("end_date"));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with order reading by id", e);
        }
        return order;
    }

    @Override
    public void update(Order order) {
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setLong(1, order.getClient().getId());
            preparedStatement.setLong(2, order.getApartment().getId());
            preparedStatement.setDate(3, (Date) order.getStartDate());
            preparedStatement.setDate(4, (Date) order.getEndDate());
            preparedStatement.setLong(5, order.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with order updating", e);
        }
    }

    @Override
    public void remove(long id) {
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with order deleting", e);
        }
    }

    @Override
    public List<Order> readAll() {
        List<Order> orderList = new ArrayList<Order>();
        Order order;
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setClient(new ClientDao().read(resultSet.getLong("client_id_fk")));
                order.setApartment(new ApartmentDao().read(resultSet.getLong("apartment_id_fk")));
                order.setStartDate(resultSet.getDate("start_date"));
                order.setEndDate(resultSet.getDate("end_date"));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with order readingAll", e);
        }
        return orderList;
    }
}
