package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketService {

    public String reserveTicket(Connection conn, int personId, int showtimeId, String seatRow, int seatColumn) {
        String status = "Reservation Failed";

        try {
            // Find seat ID based on the row and column
            String sqlFindSeat = "SELECT seat_id, taken FROM Seat WHERE row = ? AND column_number = ?";
            PreparedStatement stmtFindSeat = conn.prepareStatement(sqlFindSeat);
            stmtFindSeat.setString(1, seatRow);
            stmtFindSeat.setInt(2, seatColumn);

            var rs = stmtFindSeat.executeQuery();
            if (rs.next()) {
                int seatId = rs.getInt("seat_id");
                boolean taken = rs.getBoolean("taken");

                if (taken) {
                    status = "Seat Already Taken";
                } else {
                    // Reserve the seat by inserting into Ticket table
                    String sqlReserve = "INSERT INTO Ticket (seat_id, showtime_id, customer_id, status) VALUES (?, ?, ?, 'active')";
                    PreparedStatement stmtReserve = conn.prepareStatement(sqlReserve);
                    stmtReserve.setInt(1, seatId);
                    stmtReserve.setInt(2, showtimeId);
                    stmtReserve.setInt(3, personId);
                    stmtReserve.executeUpdate();

                    // Mark the seat as taken
                    String sqlUpdateSeat = "UPDATE Seat SET taken = TRUE WHERE seat_id = ?";
                    PreparedStatement stmtUpdateSeat = conn.prepareStatement(sqlUpdateSeat);
                    stmtUpdateSeat.setInt(1, seatId);
                    stmtUpdateSeat.executeUpdate();

                    status = "Reservation Successful";
                }
            } else {
                status = "Seat Not Found";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }
}
