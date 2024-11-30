//movieservice.java
package util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieService {

    // Method to view available movies (this already exists)
    public static void viewAvailableMovies(Connection conn) {
        String query = "SELECT movie_id, title, genre, description FROM Movie";

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n=== Available Movies ===");
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
            }

            if (!hasMovies) {
                System.out.println("No movies are currently available.\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while retrieving available movies.\n");
        }
    }

    // Method to view available showtimes for a specific movie
    public static void viewAvailableShowtimes(Connection conn, int movieId) {
        String query = "SELECT showtime_id, show_time FROM Showtime WHERE movie_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, movieId);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n=== Available Showtimes ===");
            boolean hasShowtimes = false;

            while (rs.next()) {
                hasShowtimes = true;
                int showtimeId = rs.getInt("showtime_id");
                String showTime = rs.getString("show_time");

                System.out.println("Showtime ID: " + showtimeId + " - Time: " + showTime);
            }

            if (!hasShowtimes) {
                System.out.println("No showtimes available for this movie.\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while retrieving showtimes.\n");
        }
    }
}
