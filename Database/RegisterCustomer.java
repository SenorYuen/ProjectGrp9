// RegisterCustomer.java
package Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RegisterCustomer {
    private Scanner scanner = new Scanner(System.in);

    public void register(Connection conn) {
        System.out.println("\n=== Register as a Registered User ===");
        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine().trim();

            System.out.print("Enter Address: ");
            String address = scanner.nextLine().trim();

            System.out.print("Enter Card Number: ");
            String cardNumber = scanner.nextLine().trim();

            // Insert into Person table with user_type 'registered'
            String sql = "INSERT INTO Person (name, email, password_hash, user_type, created_at, updated_at) VALUES (?, ?, ?, 'registered', NOW(), NOW())";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password); // No hashing as per your request

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Registration successful! You can now log in.");
            } else {
                System.out.println("Registration failed. Please try again.");
            }

            pstmt.close();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate entry
                System.out.println("Registration failed: Email already exists.");
            } else {
                System.out.println("An error occurred during registration.");
                e.printStackTrace();
            }
        }
    }
}
