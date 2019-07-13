package com.project.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionUtil {
    public static Connection getConnection(String driverName, String url, String user, String pass) {
        Connection connection = null;
        Logger logger = MyLogger.getLogger();

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            logger.info(driverName + " is not found. Include it in your library path ");
            e.printStackTrace();
            return connection;
        }
        logger.info(driverName + " successfully connected");

        try {
            connection = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {
            logger.info("Connection Failed");
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


    public void test() {

    }
}
