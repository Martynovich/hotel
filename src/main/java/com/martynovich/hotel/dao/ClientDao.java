package com.martynovich.hotel.dao;

import com.martynovich.hotel.domain.Client;
import com.martynovich.hotel.util.ConnectionPoint;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @author
 */
public class ClientDao implements IClientDao {

    final static Logger LOGGER = Logger.getLogger(ClientDao.class);
    private  static final String INSERT_QUERY = "INSERT INTO clients " +
            "(login, password, name, surname, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_QUERY = "SELECT * FROM hotel.clients WHERE login = ?";
    private static final String READ_BY_ID_QUERY = "SELECT * FROM hotel.clients WHERE id = ?";
    private static final String UPDATE_QUERY =
            "UPDATE hotel.clients " +
                    "SET login = ?, password = ?, name = ?, surname = ?, phone = ?, email = ?" +
                    "WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM hotel.clients WHERE id = ?";
    private static final String READ_ALL_QUERY = "SELECT * FROM hotel.clients";
    private ConnectionPoint connectionPoint;

    public ClientDao() {
        this.connectionPoint = ConnectionPoint.getInstance();
    }

    @Override
    public void insert(Client client) {
        LOGGER.info("Client inserting started");
        try (Connection connection = connectionPoint.getConnection()) {
            connection.prepareStatement(INSERT_QUERY);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, client.getPassword());
            preparedStatement.setString(3, client.getName());
            preparedStatement.setString(4, client.getSurname());
            preparedStatement.setString(5, client.getPhone());
            preparedStatement.setString(6, client.getEmail());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with client inserting", e);
        }
    }

    @Override
    public Client read(String login) {
        Client client = new Client();
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_QUERY);
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                client.setId(resultSet.getInt("id"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setName(resultSet.getString("name"));
                client.setSurname(resultSet.getString("surname"));
                client.setPhone(resultSet.getString("phone"));
                client.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with client reading", e);
        }
        return client;
    }

    @Override
    public Client read(long id) {
        Client client = new Client();
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                client.setId(resultSet.getInt("id"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setName(resultSet.getString("name"));
                client.setSurname(resultSet.getString("surname"));
                client.setPhone(resultSet.getString("phone"));
                client.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with client reading by id", e);
        }
        return client;
    }

    @Override
    public void update(Client client) {
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, client.getPassword());
            preparedStatement.setString(3, client.getName());
            preparedStatement.setString(4, client.getSurname());
            preparedStatement.setString(5, client.getPhone());
            preparedStatement.setString(6, client.getEmail());
            preparedStatement.setLong(7, client.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with client updating", e);
        }
    }

    @Override
    public void remove(long id) {
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with client deleting", e);
        }
    }

    @Override
    public List<Client> readAll() {
        List<Client> clientList = new ArrayList<Client>();
        Client client;
        try(Connection connection = connectionPoint.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setName(resultSet.getString("name"));
                client.setSurname(resultSet.getString("surname"));
                client.setPhone(resultSet.getString("phone"));
                client.setEmail(resultSet.getString("email"));
                clientList.add(client);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL troubles with client readingAll", e);
        }
        return clientList;
    }

    //public static void main(String[] args) {
        /*Client client = new Client();
        client.setId(5);client.setLogin("Padla");client.setPassword("555");client.setName("Padla");
        client.setSurname("Loh");client.setPhone("25674");client.setEmail("shlyocik@com.ua");
        new ClientDao().insert(client);*/
        //new ClientDao().remove(9);
        /*List<Client> list = new ClientDao().readAll();
        for(Client client: list){
            LOGGER.info(client);
        }*/

        //Client client = new ClientDao().read("Oleg");
        //System.out.println(client.getId() + client.getLogin() + client.getSurname());

    //}
}
