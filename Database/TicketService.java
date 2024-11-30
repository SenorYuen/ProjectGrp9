package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketService {
    public static void buyTicket(Connection conn, int showtimeId, int seatId, String cardNumber) {
        // Your existing code logic to buy the ticket, just make sure to handle cardNumber as String
        String query = "INSERT INTO Ticket (showtime_id, seat_id, card_number) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, showtimeId);
            pstmt.setInt(2, seatId);
            pstmt.setString(3, cardNumber);  // Use setString for cardNumber (which is a String)
            pstmt.executeUpdate();
            System.out.println("Ticket purchased successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error purchasing ticket.");
        }
    }
}

