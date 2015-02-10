package com.martynovich.hotel.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author
 */
public class ConnectionPoint {
    private static ConnectionPoint instance;
    final static Logger LOGGER = Logger.getLogger(ConnectionPoint.class);
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private ConnectionPoint(){}

    public static ConnectionPoint getInstance(){
        if(instance==null ){
        return new ConnectionPoint();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                        LOGGER.info("Connection created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
