// DBConnection.java
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/movietheaterdb";
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASSWORD = "IloveJackie123!"; // Replace with your DB password
    private static Connection connection = null;

    // Private constructor to prevent instantiation
    private DBConnection() { }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establish the connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully.");
            } catch (ClassNotFoundException e) {
                System.out.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
