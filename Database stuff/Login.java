package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    // This method authenticates the user by email and password
    public int authenticateUser(Connection conn) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for email and password
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Query to check if the email and password match
        String query = "SELECT reg_customer_id, email, password FROM RegisteredCustomer WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            // Check if the user exists and credentials are correct
            if (rs.next()) {
                String dbEmail = rs.getString("email");
                int customerId = rs.getInt("reg_customer_id");

                // Print out successful login info
                System.out.println("Login successful! Welcome, " + dbEmail);

                // Return the customer ID after successful login
                return customerId;
            } else {
                System.out.println("Invalid login credentials.");
                return -1; // Return -1 to indicate failed login
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred during authentication.");
            return -1; // Return -1 to indicate failed login
        }
    }
}
