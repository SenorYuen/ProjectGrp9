package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection connect() throws SQLException {
        // JDBC connection details (update with your database info)
        String url = "jdbc:mysql://localhost:3306/movietheaterdb";
        String username = "root";
        String password = "IloveJackie123!";

        try {
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (SQLException e) {
            throw new SQLException("Connection to the database failed.", e);
        }
    }
}
