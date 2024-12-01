package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketService {
//	public String reserveTicket(Connection conn, int personId, int showtimeId, String seatRow, int seatColumn) {
//	    String status = "Reservation Failed";
//
//	    try {
//	        // Find seat ID based on the row and column
//	        String sqlFindSeat = "SELECT seat_id, taken FROM Seat WHERE `row` = ? AND column_number = ?";
//	        PreparedStatement stmtFindSeat = conn.prepareStatement(sqlFindSeat);
//	        stmtFindSeat.setString(1, seatRow);
//	        stmtFindSeat.setInt(2, seatColumn);
//
//	        var rs = stmtFindSeat.executeQuery();
//	        if (rs.next()) {
//	            int seatId = rs.getInt("seat_id");
//	            boolean taken = rs.getBoolean("taken");
//
//	            // Check if the seat is already reserved for the selected showtime
//	            String sqlCheckTicket = "SELECT COUNT(*) FROM Ticket WHERE seat_id = ? AND showtime_id = ?";
//	            PreparedStatement stmtCheckTicket = conn.prepareStatement(sqlCheckTicket);
//	            stmtCheckTicket.setInt(1, seatId);
//	            stmtCheckTicket.setInt(2, showtimeId);
//
//	            var rsCheck = stmtCheckTicket.executeQuery();
//	            if (rsCheck.next() && rsCheck.getInt(1) > 0) {
//	                status = "Seat Already Reserved for This Showtime";
//	            } else if (taken) {
//	                status = "Seat Already Taken";
//	            } else {
//	                // Reserve the seat by inserting into Ticket table
//	                String sqlReserve = "INSERT INTO Ticket (seat_id, showtime_id, customer_id, status) VALUES (?, ?, ?, 'active')";
//	                PreparedStatement stmtReserve = conn.prepareStatement(sqlReserve);
//	                stmtReserve.setInt(1, seatId);
//	                stmtReserve.setInt(2, showtimeId);
//	                stmtReserve.setInt(3, personId);
//	                stmtReserve.executeUpdate();
//
//	                // Mark the seat as taken in the Seat table
//	                String sqlUpdateSeat = "UPDATE Seat SET taken = TRUE WHERE seat_id = ?";
//	                PreparedStatement stmtUpdateSeat = conn.prepareStatement(sqlUpdateSeat);
//	                stmtUpdateSeat.setInt(1, seatId);
//	                stmtUpdateSeat.executeUpdate();
//
//	                status = "Reservation Successful";
//	            }
//	        } else {
//	            status = "Seat Not Found";
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//
//	    return status;
//	}

	public String reserveTicket(Connection conn, int personId, int showtimeId, String seatRow, int seatColumn) {
	    String status = "Reservation Failed";
	    String receipt = ""; // Initialize receipt string

	    try {
	        // Find seat ID based on the row and column
	        String sqlFindSeat = "SELECT seat_id, taken FROM Seat WHERE `row` = ? AND column_number = ?";
	        PreparedStatement stmtFindSeat = conn.prepareStatement(sqlFindSeat);
	        stmtFindSeat.setString(1, seatRow);
	        stmtFindSeat.setInt(2, seatColumn);

	        var rs = stmtFindSeat.executeQuery();
	        if (rs.next()) {
	            int seatId = rs.getInt("seat_id");
	            boolean taken = rs.getBoolean("taken");

	            // Check if the seat is already reserved for the selected showtime
	            String sqlCheckTicket = "SELECT COUNT(*) FROM Ticket WHERE seat_id = ? AND showtime_id = ?";
	            PreparedStatement stmtCheckTicket = conn.prepareStatement(sqlCheckTicket);
	            stmtCheckTicket.setInt(1, seatId);
	            stmtCheckTicket.setInt(2, showtimeId);

	            var rsCheck = stmtCheckTicket.executeQuery();
	            if (rsCheck.next() && rsCheck.getInt(1) > 0) {
	                status = "Seat Already Reserved for This Showtime";
	            } else if (taken) {
	                status = "Seat Already Taken";
	            } else {
	                // Reserve the seat by inserting into Ticket table
	                String sqlReserve = "INSERT INTO Ticket (seat_id, showtime_id, customer_id, status) VALUES (?, ?, ?, 'active')";
	                PreparedStatement stmtReserve = conn.prepareStatement(sqlReserve, Statement.RETURN_GENERATED_KEYS);
	                stmtReserve.setInt(1, seatId);
	                stmtReserve.setInt(2, showtimeId);
	                stmtReserve.setInt(3, personId);
	                stmtReserve.executeUpdate();

	                // Get the generated ticket ID
	                ResultSet generatedKeys = stmtReserve.getGeneratedKeys();
	                int ticketId = 0;
	                if (generatedKeys.next()) {
	                    ticketId = generatedKeys.getInt(1);
	                }

	                // Mark the seat as taken
	                String sqlUpdateSeat = "UPDATE Seat SET taken = TRUE WHERE seat_id = ?";
	                PreparedStatement stmtUpdateSeat = conn.prepareStatement(sqlUpdateSeat);
	                stmtUpdateSeat.setInt(1, seatId);
	                stmtUpdateSeat.executeUpdate();

	                status = "Reservation Successful";

	                // Fetch the movie name (combined query)
	                String sqlGetMovieName = "SELECT title FROM Movie WHERE movie_id = (SELECT movie_id FROM Showtime WHERE showtime_id = ?)";
	                PreparedStatement stmtGetMovieName = conn.prepareStatement(sqlGetMovieName);
	                stmtGetMovieName.setInt(1, showtimeId);  // Use the showtimeId to get the movie title
	                var rsMovie = stmtGetMovieName.executeQuery();
	                String movieName = "Unknown Movie"; // Default value if no movie found
	                if (rsMovie.next()) {
	                    movieName = rsMovie.getString("title");
	                }

	                // Build the receipt string
	                receipt = String.format("Receipt:\nTicket ID: %d\nMovie: %s\nShowtime ID: %d\nSeat: %s%d\nStatus: %s\nReservation Date: %s",
	                        ticketId, movieName, showtimeId, seatRow, seatColumn, "Active", new java.util.Date());
	            }
	        } else {
	            status = "Seat Not Found";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return status + "\n" + receipt; // Return the status and receipt
	}

}
