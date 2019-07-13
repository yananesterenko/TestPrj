package com.project.dao;

import com.project.utils.MyLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionPostgress {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://127.0.0.1:5433/myapp_data";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";


    public Connection getConnection() {
        Connection connection = null;
        Logger logger = MyLogger.getLogger();

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.info(DRIVER + " is not found. Include it in your library path ");
            e.printStackTrace();
            return connection;
        }
        logger.info(DRIVER + " successfully connected");

        try {
            connection = DriverManager.getConnection(URL, USER, PASS);

        } catch (SQLException e) {
            logger.severe("Connection Failed");
            //e.printStackTrace();
            logger.info("Check if port and Database name are correct.");
            return connection;
        }
        if (connection != null) {
            logger.info("You successfully connected to database now");
        } else {
            logger.info("Failed to make connection to database");
        }
        return connection;

    }
}

