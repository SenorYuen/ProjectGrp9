package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // Database connection setup
        String url = "jdbc:mysql://localhost:3306/movietheaterdb"; // Update with your database URL
        String username = "root"; // Update with your DB username
        String password = "IloveJackie123!"; // Update with your DB password

        // Establish the connection
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected successfully!");

            // Start the login flow
            Login login = new Login();
            int customerId = login.authenticateUser(conn);

            if (customerId != -1) {  // If login is successful
                // After successful login, show the user menu
                UserMenu userMenu = new UserMenu(conn, customerId); // Pass customer ID
                userMenu.displayMenu();
            } else {
                System.out.println("Login failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection failed.");
        }
    }
}
