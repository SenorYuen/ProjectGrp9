package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterCustomer {

    public String registerNewCustomer(Connection conn, String name, String email, String password, String address, String cardNumber) {
        String status = "Registration Failed";

        try {
            // Start transaction
            conn.setAutoCommit(false);

            // Step 1: Insert into Person table
            String sqlPerson = "INSERT INTO Person (name, email, password_hash, user_type) VALUES (?, ?, SHA2(?, 256), 'registered')";
            try (PreparedStatement stmtPerson = conn.prepareStatement(sqlPerson)) {
                stmtPerson.setString(1, name);
                stmtPerson.setString(2, email);
                stmtPerson.setString(3, password);  // SHA256 will be applied in the SQL query
                stmtPerson.executeUpdate();
            }

            // Step 2: Insert into RegisteredCustomer table
            String sqlRegisteredCustomer = "INSERT INTO RegisteredCustomer (name, email, password, address, card_number) VALUES (?, ?, SHA2(?, 256), ?, ?)";
            try (PreparedStatement stmtRegCustomer = conn.prepareStatement(sqlRegisteredCustomer)) {
                stmtRegCustomer.setString(1, name);
                stmtRegCustomer.setString(2, email);
                stmtRegCustomer.setString(3, password); // Hash the password
                stmtRegCustomer.setString(4, address);
                stmtRegCustomer.setString(5, cardNumber);
                stmtRegCustomer.executeUpdate();
            }

            // Commit transaction
            conn.commit();
            status = "Registration Successful!";
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                status = "Registration Failed: " + e.getMessage();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
                status = "Registration Failed and rollback failed.";
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return status;
    }
}
