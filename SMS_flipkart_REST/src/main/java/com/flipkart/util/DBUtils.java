package com.flipkart.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type DB utils containing functions to connect and disconnect from database
 */
public class DBUtils {
    private static Connection connection = null;

    /**
     * Gets connection to a database
     *
     * @return the connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Open a connection to database
     *
     * @return the boolean to indicate whether the connection is successful
     */
    public static boolean openConnection() {
        if (connection != null)
            return true;

        try {
            Properties prop = new Properties();
            InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(inputStream);

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Close the connection to the database
     */
    public static void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
