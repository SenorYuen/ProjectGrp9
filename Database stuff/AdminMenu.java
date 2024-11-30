// AdminMenu.java
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class AdminMenu {
    private Scanner scanner = new Scanner(System.in);
    private static final int THEATER_ID = 1; // Since there's only one theater

    /**
     * Displays the Admin Menu and handles user input for various admin operations.
     *
     * @param conn The active database connection.
     */
    public void displayMenu(Connection conn) {
        int choice = -1;
        while (choice != 4) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add Movie");
            System.out.println("2. Delete Movie");
            System.out.println("3. View Movies");
            System.out.println("4. Logout");
            System.out.print("Select an option: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addMovie(conn);
                        break;
                    case 2:
                        deleteMovie(conn);
                        break;
                    case 3:
                        viewMovies(conn);
                        break;
                    case 4:
                        System.out.println("Logging out from admin account.");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    /**
     * Adds a new movie to the database and prompts the admin to add showtimes for it.
     *
     * @param conn The active database connection.
     */
    private void addMovie(Connection conn) {
        try {
            System.out.println("\n=== Add New Movie ===");
            System.out.print("Enter Movie Title: ");
            String title = scanner.nextLine().trim();

            System.out.print("Enter Genre: ");
            String genre = scanner.nextLine().trim();

            System.out.print("Enter Description: ");
            String description = scanner.nextLine().trim();

            String sql = "INSERT INTO Movie (title, genre, description) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, title);
            pstmt.setString(2, genre);
            pstmt.setString(3, description);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                int movieId = -1;
                if (generatedKeys.next()) {
                    movieId = generatedKeys.getInt(1);
                }
                System.out.println("Movie added successfully with Movie ID: " + movieId);
                // Prompt to add showtimes
                addShowtimesForMovie(conn, movieId);
            } else {
                System.out.println("Failed to add movie.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the movie.");
            e.printStackTrace();
        }
    }

    private void addShowtimesForMovie(Connection conn, int movieId) {
        try {
            System.out.println("\n=== Add Showtimes for Movie ID: " + movieId + " ===");
            boolean addMore = true;
            while (addMore) {
                // Prompt for Show Date
                System.out.print("Enter Show Date (YYYY-MM-DD): ");
                String showDateStr = scanner.nextLine().trim();

                // Parse date
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate showDate;
                try {
                    showDate = LocalDate.parse(showDateStr, dateFormatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
                    continue;
                }

                // Prompt for Show Time
                System.out.print("Enter Showtime (HH:MM, 24-hour format): ");
                String showTimeStr = scanner.nextLine().trim();

                // Parse time
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime showTime;
                try {
                    showTime = LocalTime.parse(showTimeStr, timeFormatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid time format. Please enter time in HH:MM format.");
                    continue;
                }

                String sql = "INSERT INTO Showtime (movie_id, theater_id, show_date, show_time) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, movieId);
                pstmt.setInt(2, THEATER_ID); // Assuming one theater
                pstmt.setDate(3, Date.valueOf(showDate));
                pstmt.setTime(4, Time.valueOf(showTime));

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Showtime added successfully.");
                } else {
                    System.out.println("Failed to add showtime.");
                }

                pstmt.close();

                // Ask if admin wants to add more showtimes
                System.out.print("Do you want to add another showtime for this movie? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("yes")) {
                    addMore = false;
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the showtime.");
            e.printStackTrace();
        }
    }


    /**
     * Deletes a movie from the database based on the provided movie ID.
     *
     * @param conn The active database connection.
     */
    private void deleteMovie(Connection conn) {
        try {
            System.out.println("\n=== Delete Movie ===");
            System.out.print("Enter Movie ID to Delete: ");
            String input = scanner.nextLine().trim();
            int movieId;
            try {
                movieId = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Movie ID. Please enter a numeric value.");
                return;
            }

            // Optional: Confirm deletion
            System.out.print("Are you sure you want to delete Movie ID " + movieId + "? This will remove all associated showtimes. (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (!confirmation.equals("yes")) {
                System.out.println("Deletion canceled.");
                return;
            }

            String sql = "DELETE FROM Movie WHERE movie_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Movie deleted successfully.");
            } else {
                System.out.println("Failed to delete movie. Please ensure the Movie ID is correct.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the movie.");
            e.printStackTrace();
        }
    }

    /**
     * Displays all movies along with their associated showtimes.
     *
     * @param conn The active database connection.
     */
    private void viewMovies(Connection conn) {
        try {
            String sql = "SELECT m.movie_id, m.title, m.genre, m.description, GROUP_CONCAT(s.show_time ORDER BY s.show_time SEPARATOR ', ') AS showtimes " +
                         "FROM Movie m LEFT JOIN Showtime s ON m.movie_id = s.movie_id " +
                         "GROUP BY m.movie_id";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n=== Movies ===");
            while (rs.next()) {
                int movieId = rs.getInt("movie_id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String description = rs.getString("description");
                String showtimes = rs.getString("showtimes");

                System.out.println("\nMovie ID: " + movieId);
                System.out.println("Title: " + title);
                System.out.println("Genre: " + genre);
                System.out.println("Description: " + description);
                if (showtimes != null && !showtimes.isEmpty()) {
                    System.out.println("Showtimes: " + showtimes);
                } else {
                    System.out.println("No showtimes scheduled.");
                }
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving movies.");
            e.printStackTrace();
        }
    }
    public static void showAvailableShowtimes(Connection conn, int movieId) {
        String query = "SELECT showtime_id, show_time FROM Showtime WHERE movie_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, movieId);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("\nAvailable Showtimes:");
            boolean hasShowtimes = false;
            
            while (rs.next()) {
                hasShowtimes = true;
                int showtimeId = rs.getInt("showtime_id");
                String showTime = rs.getString("show_time");
                
                System.out.println("Showtime ID: " + showtimeId + " | " + showTime);
            }
            
            if (!hasShowtimes) {
                System.out.println("No showtimes available for this movie.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching showtimes.");
        }
    }

}
