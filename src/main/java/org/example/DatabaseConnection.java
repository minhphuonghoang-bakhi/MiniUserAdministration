package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DriverManager class offers several overloaded getConnection methods to establish a connection to a database.
 * These methods attempt to select an appropriate driver from the set of registered JDBC drivers.
 */
public class DatabaseConnection {
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    //wrapper method so later: Connection conn = DatabaseConnection.getConnection();
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);  //return object of Connection type
    }
}
