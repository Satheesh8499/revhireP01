package org.revhire.util;

import java.sql.*;

public class ConnectionUtil {

    private static Connection connection;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/revhire1";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "beastF5$@";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                synchronized (DatabaseConnection.class) {
                    if (connection == null || connection.isClosed()) {
                        try {
                            Class.forName(JDBC_DRIVER);
                            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException("JDBC Driver not found: " + e.getMessage(), e);
                        } catch (SQLException e) {
                            throw new RuntimeException("Failed to establish a database connection: " + e.getMessage(), e);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
