package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketService {
//
//    public String reserveTicket(Connection conn, int personId, int showtimeId, String seatRow, int seatColumn) {
//        String status = "Reservation Failed";
//        String receipt = ""; // Initialize receipt string
//
//        try {
//            // Find seat ID based on the row and column
//            String sqlFindSeat = "SELECT seat_id, taken FROM Seat WHERE `row` = ? AND column_number = ?";
//            PreparedStatement stmtFindSeat = conn.prepareStatement(sqlFindSeat);
//            stmtFindSeat.setString(1, seatRow);
//            stmtFindSeat.setInt(2, seatColumn);
//
//            var rs = stmtFindSeat.executeQuery();
//            if (rs.next()) {
//                int seatId = rs.getInt("seat_id");
//                boolean taken = rs.getBoolean("taken");
//
//                // Check if the seat is already reserved for the selected showtime
//                String sqlCheckTicket = "SELECT COUNT(*) FROM Ticket WHERE seat_id = ? AND showtime_id = ?";
//                PreparedStatement stmtCheckTicket = conn.prepareStatement(sqlCheckTicket);
//                stmtCheckTicket.setInt(1, seatId);
//                stmtCheckTicket.setInt(2, showtimeId);
//
//                var rsCheck = stmtCheckTicket.executeQuery();
//                if (rsCheck.next() && rsCheck.getInt(1) > 0) {
//                    status = "Seat Already Reserved for This Showtime";
//                } else if (taken) {
//                    status = "Seat Already Taken";
//                } else {
//                    // Reserve the seat by inserting into Ticket table
//                    String sqlReserve = "INSERT INTO Ticket (seat_id, showtime_id, customer_id, status) VALUES (?, ?, ?, 'active')";
//                    PreparedStatement stmtReserve = conn.prepareStatement(sqlReserve, Statement.RETURN_GENERATED_KEYS);
//                    stmtReserve.setInt(1, seatId);
//                    stmtReserve.setInt(2, showtimeId);
//                    stmtReserve.setInt(3, personId);
//                    stmtReserve.executeUpdate();
//
//                    // Get the generated ticket ID
//                    ResultSet generatedKeys = stmtReserve.getGeneratedKeys();
//                    int ticketId = 0;
//                    if (generatedKeys.next()) {
//                        ticketId = generatedKeys.getInt(1);
//                    }
//
//                    // Mark the seat as taken
//                    String sqlUpdateSeat = "UPDATE Seat SET taken = TRUE WHERE seat_id = ?";
//                    PreparedStatement stmtUpdateSeat = conn.prepareStatement(sqlUpdateSeat);
//                    stmtUpdateSeat.setInt(1, seatId);
//                    stmtUpdateSeat.executeUpdate();
//
//                    status = "Reservation Successful";
//
//                    // Fetch the movie ID and name (combined query)
//                    String sqlGetMovie = "SELECT movie_id, title FROM Movie WHERE movie_id = (SELECT movie_id FROM Showtime WHERE showtime_id = ?)";
//                    PreparedStatement stmtGetMovie = conn.prepareStatement(sqlGetMovie);
//                    stmtGetMovie.setInt(1, showtimeId);  // Use the showtimeId to get the movie details
//                    var rsMovie = stmtGetMovie.executeQuery();
//                    int movieId = 0;
//                    String movieName = "Unknown Movie"; // Default value if no movie found
//                    if (rsMovie.next()) {
//                        movieId = rsMovie.getInt("movie_id");
//                        movieName = rsMovie.getString("title");
//                    }
//
//                    // Assuming movieCost is fixed (you can update this if the price varies)
//                    double movieCost = 10.00; // Example: $10
//
//                    // Fetch the card number
//                    String sqlGetCardNumber = "SELECT card_number FROM RegisteredCustomer WHERE reg_customer_id = ?";
//                    PreparedStatement stmtGetCardNumber = conn.prepareStatement(sqlGetCardNumber);
//                    stmtGetCardNumber.setInt(1, personId); // Using personId or customer ID here
//                    ResultSet rsCard = stmtGetCardNumber.executeQuery();
//                    String cardNumber = "";
//                    if (rsCard.next()) {
//                        cardNumber = rsCard.getString("card_number");
//                    }
//
//                    // Create the receipt string (including purchase_date)
//                    receipt = String.format("Receipt:\nTicket ID: %d\nMovie: %s\nShowtime ID: %d\nSeat: %s%d\nStatus: %s\nReservation Date: %s\nPrice: $%.2f\nCard Number: %s",
//                            ticketId, movieName, showtimeId, seatRow, seatColumn, "Active", new java.util.Date(), movieCost, cardNumber);
//
//                    // Insert the receipt information into the Receipt table
//                    String sqlInsertReceipt = "INSERT INTO Receipt (ticket_id, movie_id, seat_id, price, card_number, purchase_date) VALUES (?, ?, ?, ?, ?, ?)";
//                    PreparedStatement stmtInsertReceipt = conn.prepareStatement(sqlInsertReceipt);
//                    stmtInsertReceipt.setInt(1, ticketId);        // ticket_id
//                    stmtInsertReceipt.setInt(2, movieId);         // movie_id (from the movie selection)
//                    stmtInsertReceipt.setInt(3, seatId);          // seat_id (from the seat selection)
//                    stmtInsertReceipt.setBigDecimal(4, new java.math.BigDecimal(movieCost)); // price
//                    stmtInsertReceipt.setString(5, cardNumber);   // card_number
//                    stmtInsertReceipt.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis())); // purchase_date
//
//                    stmtInsertReceipt.executeUpdate();
//                }
//            } else {
//                status = "Seat Not Found";
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return status + "\n" + receipt; // Return the status and receipt
//    }
	public String reserveTicket(Connection conn, Integer personId, int showtimeId, String seatRow, int seatColumn, String cardNumber) {
	    String status = "Reservation Failed";
	    String receipt = ""; // Initialize receipt string
	    int customerId = -1;

	    try {
	        // If the person is not logged in (personId == null), try to find the customer in the RegisteredCustomer table using the card number
	        if (personId == null && cardNumber != null && !cardNumber.trim().isEmpty()) {
	            // Check if the card number exists in the RegisteredCustomer table
	            String sqlCheckCardNumber = "SELECT reg_customer_id FROM RegisteredCustomer WHERE card_number = ?";
	            PreparedStatement stmtCheckCard = conn.prepareStatement(sqlCheckCardNumber);
	            stmtCheckCard.setString(1, cardNumber);
	            ResultSet rsCard = stmtCheckCard.executeQuery();

	            if (rsCard.next()) {
	                // If the card number exists, get the corresponding reg_customer_id
	                customerId = rsCard.getInt("reg_customer_id");
	            } else {
	                // If the card number doesn't exist, create a new customer record
	                String sqlInsertCustomer = "INSERT INTO RegisteredCustomer (card_number) VALUES (?)";
	                PreparedStatement stmtInsertCustomer = conn.prepareStatement(sqlInsertCustomer, Statement.RETURN_GENERATED_KEYS);
	                stmtInsertCustomer.setString(1, cardNumber);
	                stmtInsertCustomer.executeUpdate();

	                ResultSet generatedKeys = stmtInsertCustomer.getGeneratedKeys();
	                if (generatedKeys.next()) {
	                    customerId = generatedKeys.getInt(1); // Get the newly inserted reg_customer_id
	                }
	            }
	        } else if (personId != null) {
	            // If personId is not null, use it as the customerId for logged-in users
	            customerId = personId;
	        }

	        if (customerId == -1) {
	            return "Unable to determine customer. Please login or provide a valid card number.";
	        }

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
	                stmtReserve.setInt(3, customerId);
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

	                // Fetch the movie ID and name (combined query)
	                String sqlGetMovie = "SELECT movie_id, title FROM Movie WHERE movie_id = (SELECT movie_id FROM Showtime WHERE showtime_id = ?)";
	                PreparedStatement stmtGetMovie = conn.prepareStatement(sqlGetMovie);
	                stmtGetMovie.setInt(1, showtimeId);  // Use the showtimeId to get the movie details
	                var rsMovie = stmtGetMovie.executeQuery();
	                int movieId = 0;
	                String movieName = "Unknown Movie"; // Default value if no movie found
	                if (rsMovie.next()) {
	                    movieId = rsMovie.getInt("movie_id");
	                    movieName = rsMovie.getString("title");
	                }

	                // Assuming movieCost is fixed
	                int movieCost = 10;

	                // Create the receipt string
	                receipt = String.format("Receipt:\nTicket ID: %d\nMovie: %s\nShowtime ID: %d\nSeat: %s%d\nStatus: %s\nReservation Date: %s\nPrice: $%d\nCard Number: %s",
	                        ticketId, movieName, showtimeId, seatRow, seatColumn, "Active", new java.util.Date(), movieCost, cardNumber);

	                // Now insert the receipt information into the Receipt table
	                String sqlInsertReceipt = "INSERT INTO Receipt (ticket_id, movie_id, seat_id, price, card_number, purchase_date) VALUES (?, ?, ?, ?, ?, ?)";
	                PreparedStatement stmtInsertReceipt = conn.prepareStatement(sqlInsertReceipt);
	                stmtInsertReceipt.setInt(1, ticketId);        // ticket_id
	                stmtInsertReceipt.setInt(2, movieId);         // movie_id (from the movie selection)
	                stmtInsertReceipt.setInt(3, seatId);          // seat_id (from the seat selection)
	                stmtInsertReceipt.setBigDecimal(4, new java.math.BigDecimal(movieCost)); // price
	                stmtInsertReceipt.setString(5, cardNumber);   // card_number
	                stmtInsertReceipt.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis())); // purchase_date

	                stmtInsertReceipt.executeUpdate();
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
