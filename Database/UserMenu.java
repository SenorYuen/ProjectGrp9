package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserMenu {

    private Connection conn;
    private int customerId;

    // Constructor accepting Connection and customerId
    public UserMenu(Connection conn, int customerId) {
        this.conn = conn;
        this.customerId = customerId;
    }
    public UserMenu(Connection conn, int customerId, String userType) {
        this.conn = conn;
        this.customerId = customerId;
        // You can store userType here if needed, or use it to modify behavior.
    }


    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. View Available Movies");
            System.out.println("2. Buy a Ticket");
            System.out.println("3. View My Tickets");
            System.out.println("4. View My Receipts");
            System.out.println("5. Logout");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    viewAvailableMovies();
                    break;
                case 2:
                    buyTicketFlow();
                    break;
                case 3:
                    // Implement logic to view user's tickets
                    break;
                case 4:
                    // Implement logic to view user's receipts
                    break;
                case 5:
                    exit = true;
                    System.out.println("You have logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Now use the customerId when needed
    private void viewAvailableMovies() {
        // Implement this method as needed
    }

    public void buyTicketFlow() {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Show available movies
        System.out.println("\n=== Available Movies ===");
        String movieQuery = "SELECT movie_id, title, genre, description FROM Movie";
        try (PreparedStatement pstmt = conn.prepareStatement(movieQuery);
             ResultSet rs = pstmt.executeQuery()) {

            boolean hasMovies = false;

            while (rs.next()) {
                hasMovies = true;
                int movieId = rs.getInt("movie_id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String description = rs.getString("description");

                System.out.println("\nMovie ID: " + movieId);
                System.out.println("Title: " + title);
                System.out.println("Genre: " + genre);
                System.out.println("Description: " + description);

                // Call method to show showtimes for each movie
                viewAvailableShowtimes(movieId);
            }

            if (!hasMovies) {
                System.out.println("No movies available.");
                return;  // Exit if no movies found
            }

            // Step 2: Allow user to select a movie
            System.out.print("Enter the Movie ID you want to watch: ");
            int movieId = scanner.nextInt();

            // Step 3: Show available showtimes for the selected movie
            viewAvailableShowtimes(movieId);

            // Step 4: Allow user to select a showtime
            System.out.print("Enter the Showtime ID: ");
            int showtimeId = scanner.nextInt();

            // Step 5: Show seat map
            showSeatMap();

            // Step 6: Allow user to choose a seat
            System.out.print("Enter the Seat ID you want to book (0-19): ");
            int seatId = scanner.nextInt();

            // Step 7: Proceed with ticket purchase (assuming the user is registered and has a card number)
            String cardNumber = getRegisteredUserCardNumber();
            if (cardNumber != null) {
                TicketService.buyTicket(conn, showtimeId, seatId, cardNumber);
            } else {
                System.out.println("Error: No card number found for the registered user.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while retrieving available movies or showtimes.");
        }
    }

    private void viewAvailableShowtimes(int movieId) {
        String query = "SELECT showtime_id, show_time FROM Showtime WHERE movie_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, movieId);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\nAvailable Showtimes for Movie ID: " + movieId);
            boolean hasShowtimes = false;
            while (rs.next()) {
                hasShowtimes = true;
                int showtimeId = rs.getInt("showtime_id");
                String showtime = rs.getString("show_time");

                System.out.println("Showtime ID: " + showtimeId + " - Time: " + showtime);
            }

            if (!hasShowtimes) {
                System.out.println("No showtimes available for this movie.\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while retrieving showtimes.\n");
        }
    }

    private String getRegisteredUserCardNumber() {
        String query = "SELECT card_number FROM RegisteredCustomer WHERE reg_customer_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("card_number");
            } else {
                System.out.println("Error: Card number not found.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showSeatMap() {
        System.out.println("\n=== Seat Map ===");
        for (int i = 1; i <= 20; i++) {
            System.out.print(String.format("%02d ", i));
            if (i % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
