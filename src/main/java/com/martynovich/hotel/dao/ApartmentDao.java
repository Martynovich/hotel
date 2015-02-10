package com.martynovich.hotel.dao;

import com.martynovich.hotel.domain.Apartment;
import com.martynovich.hotel.util.ConnectionPoint;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class ApartmentDao implements IApartmentDao {
    private ConnectionPoint connectionPoint;
    final static Logger LOGGER = Logger.getLogger(ApartmentDao.class);
    private  static final String INSERT_QUERY = "INSERT INTO apartments " +
            "(number, rooms, free, price, level) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM hotel.apartments WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE hotel.apartments " +
                    "SET number = ?, rooms = ?, free = ?, price = ?, level = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM hotel.apartments WHERE id = ?";
    private static final String READ_ALL_QUERY = "SELECT * FROM hotel.apartments";

    public ApartmentDao(){
        this.connectionPoint = ConnectionPoint.getInstance();
    }

    @Override
    public void insert(Apartment apartment) {
        LOGGER.info("Apartment inserting started");
        try (Connection connection = connectionPoint.getConnection()) {
            connection.prepareStatement(INSERT_QUERY);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setShort(1, apartment.getNumber());
            preparedStatement.setByte(2, apartment.getRooms());
            preparedStatement.setBoolean(3, apartment.isFree());
            preparedStatement.setShort(4, apartment.getPrice());
            preparedStatement.setByte(5, apartment.getLevel());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with apartment inserting", e);
        }
    }

    @Override
    public Apartment read(long id) {
        Apartment apartment = new Apartment();
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                apartment.setId(resultSet.getInt("id"));
                apartment.setNumber(resultSet.getShort("number"));
                apartment.setRooms(resultSet.getByte("rooms"));
                apartment.setFree(resultSet.getBoolean("free"));
                apartment.setPrice(resultSet.getShort("price"));
                apartment.setLevel(resultSet.getByte("level"));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with apartment reading by id", e);
        }
        return apartment;
    }

    @Override
    public void update(Apartment apartment) {
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setShort(1, apartment.getNumber());
            preparedStatement.setByte(2, apartment.getRooms());
            preparedStatement.setBoolean(3, apartment.isFree());
            preparedStatement.setShort(4, apartment.getPrice());
            preparedStatement.setByte(5, apartment.getLevel());
            preparedStatement.setLong(6, apartment.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with apartment updating", e);
        }
    }

    @Override
    public void remove(long id) {
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with apartment deleting", e);
        }
    }

    @Override
    public List<Apartment> readAll() {
        List<Apartment> apartmentList = new ArrayList<Apartment>();
        Apartment apartment;
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                apartment = new Apartment();
                apartment.setId(resultSet.getInt("id"));
                apartment.setNumber(resultSet.getShort("number"));
                apartment.setRooms(resultSet.getByte("rooms"));
                apartment.setFree(resultSet.getBoolean("free"));
                apartment.setPrice(resultSet.getShort("price"));
                apartment.setLevel(resultSet.getByte("level"));
                apartmentList.add(apartment);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with apartment readingAll", e);
        }
        return apartmentList;
    }

    public static void main(String[] args) {
        List<Apartment> list = new ApartmentDao().readAll();
            for(Apartment a : list) {
                System.out.print(a.getId() + " ");
                System.out.print(a.getNumber() + " ");
                System.out.print(a.getRooms() + " ");
                System.out.print(a.isFree() + " ");
                System.out.print(a.getPrice() + " ");
                System.out.println(a.getLevel());
            }
    }
}
