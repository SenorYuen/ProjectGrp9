// MakeReservation.java
package Database;
//makereservation.java
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class MakeReservation {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Makes a reservation for a user by calling the MakeReservation stored procedure.
     *
     * @param conn     The active database connection.
     * @param personId The ID of the person making the reservation.
     */
    public static void makeReservation(Connection conn, int personId) {
        try {
            System.out.println("\n=== Make a Reservation ===");

            System.out.print("Enter Showtime ID: ");
            int showtimeId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Seat Row (e.g., A): ");
            String seatRow = scanner.nextLine().toUpperCase();

            System.out.print("Enter Seat Number: ");
            int seatNumber = Integer.parseInt(scanner.nextLine());

            // Call the MakeReservation stored procedure
            String callProcedure = "{CALL MakeReservation(?, ?, ?, ?, ?)}";
            try (CallableStatement cstmt = conn.prepareCall(callProcedure)) {
                cstmt.setInt(1, personId);
                cstmt.setInt(2, showtimeId);
                cstmt.setString(3, seatRow);
                cstmt.setInt(4, seatNumber);
                cstmt.registerOutParameter(5, Types.VARCHAR);

                cstmt.execute();

                String status = cstmt.getString(5);
                System.out.println("Reservation Status: " + status + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while making the reservation.\n");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid input. Please enter numeric values for Showtime ID and Seat Number.\n");
        }
    }
}
