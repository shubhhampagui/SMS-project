package com.flipkart.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Utils.
 */
public class Utils {
    /**
     * Closes statement and resultSet
     *
     * @param statement the statement
     * @param resultSet the result set
     */
    public static void closeQuery(PreparedStatement statement, ResultSet resultSet) {
        try {
            if (statement != null) {
                statement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
