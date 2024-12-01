package Boundary;

import javax.swing.*;
import util.TicketService;
import util.LoginSession;
import util.DBConnection;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MovieBrowser extends JPanel {
    // Declaration of GUI components
    private JLabel header;
    private JLabel backButton;
    private JComboBox<String> movieSelector;
    private JComboBox<String> showtimeSelector;
    private JPanel seatGrid;
    private JTextField seatSelector;
    private JLabel submitSeatSelection;
    private JLabel currentTheater;
    private JLabel showtimeLabel;
    private JLabel cancelTicketLabel;
    private JLabel developerNote;
    private JLabel annualFeeLabel;

    private LoginSession session; // To hold session info

    private static final long serialVersionUID = 1L;

    // Constructor
    public MovieBrowser(JFrame mainWindow, LoginSession backendConnector) {
        this.session = backendConnector;
        setLayout(null);

        // Initialize components
        header = new JLabel("Movie Browser");
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Calibri", Font.BOLD, 30));
        header.setBounds(520, 50, 400, 30);
        add(header);

        // Movie Selector
        JLabel movieLabel = new JLabel("Select Movie:");
        movieLabel.setHorizontalAlignment(SwingConstants.CENTER);
        movieLabel.setForeground(Color.BLACK);
        movieLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        movieLabel.setBounds(520, 100, 200, 30);
        add(movieLabel);

        movieSelector = new JComboBox<>();
        movieSelector.setBounds(730, 100, 190, 30);
        populateMovies();
        add(movieSelector);

        // Showtime Selector
        JLabel showtimeLabel = new JLabel("Select Showtime:");
        showtimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        showtimeLabel.setForeground(Color.BLACK);
        showtimeLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        showtimeLabel.setBounds(520, 150, 200, 30);
        add(showtimeLabel);

        showtimeSelector = new JComboBox<>();
        showtimeSelector.setBounds(730, 150, 190, 30);
        populateShowtimes();
        add(showtimeSelector);

        // Seat Selection
        JLabel seatLabel = new JLabel("Enter Seat (e.g., A1):");
        seatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        seatLabel.setForeground(Color.BLACK);
        seatLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        seatLabel.setBounds(520, 200, 200, 30);
        add(seatLabel);

        seatSelector = new JTextField();
        seatSelector.setBounds(730, 200, 190, 30);
        add(seatSelector);

        // Submit Seat Selection
        submitSeatSelection = new JLabel("Reserve Seat");
        submitSeatSelection.setHorizontalAlignment(SwingConstants.CENTER);
        submitSeatSelection.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitSeatSelection.setFont(new Font("Calibri", Font.PLAIN, 23));
        submitSeatSelection.setBounds(520, 250, 400, 30);
        submitSeatSelection.setForeground(Color.BLACK);
        add(submitSeatSelection);

        // Add listener to reserve seat
        submitSeatSelection.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                reserveSeat(mainWindow);
            }
        });

        // Back button
        backButton = new JLabel("Return Home");
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        backButton.setBounds(20, 20, 200, 30);
        backButton.setForeground(Color.BLACK);
        add(backButton);

        // Back button listener
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Homepage homepage = new Homepage(mainWindow);
                mainWindow.setContentPane(homepage);
                mainWindow.revalidate();
            }
        });

        // Annual Fee Label for registered users
        if (session.isAuthenticated() && "registered".equals(session.getUserType())) {
            annualFeeLabel = new JLabel("Pay Annual membership Fee");
            annualFeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            annualFeeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            annualFeeLabel.setFont(new Font("Calibri", Font.PLAIN, 23));
            annualFeeLabel.setBounds(520, 300, 400, 30);
            annualFeeLabel.setForeground(Color.BLACK);
            // Add listener to invoke a payment received notification for registered users
            annualFeeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(null, "Payment Received, Thank You! (add date time functionality later)");
                }
            });
            add(annualFeeLabel);
        }

        // Developer note
        developerNote = new JLabel("Developed by: Arsalan Khaleel, Fahmi Sardar, Zaid Shaikh, Adam Yuen");
        developerNote.setHorizontalAlignment(SwingConstants.CENTER);
        developerNote.setForeground(Color.BLACK);
        developerNote.setFont(new Font("Calibri", Font.PLAIN, 12));
        developerNote.setBounds(520, 650, 400, 30);
        add(developerNote);

        setVisible(true);
    }

    // Method to populate movies from database
    private void populateMovies() {
        try (Connection conn = DBConnection.connect()) {
            String sql = "SELECT title FROM Movie";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                movieSelector.addItem(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load movies.");
        }
    }

    // Method to populate showtimes based on selected movie
    private void populateShowtimes() {
        try (Connection conn = DBConnection.connect()) {
            // First, get the selected movie's ID
            String selectedMovie = (String) movieSelector.getSelectedItem();
            if (selectedMovie == null) {
                return;
            }
            String sqlGetMovieId = "SELECT movie_id FROM Movie WHERE title = ?";
            PreparedStatement stmtGetMovieId = conn.prepareStatement(sqlGetMovieId);
            stmtGetMovieId.setString(1, selectedMovie);
            ResultSet rsMovie = stmtGetMovieId.executeQuery();
            if (rsMovie.next()) {
                int movieId = rsMovie.getInt("movie_id");

                // Now, get showtimes for this movie
                String sqlShowtimes = "SELECT show_time FROM Showtime WHERE movie_id = ?";
                PreparedStatement stmtShowtimes = conn.prepareStatement(sqlShowtimes);
                stmtShowtimes.setInt(1, movieId);
                ResultSet rsShowtimes = stmtShowtimes.executeQuery();
                while (rsShowtimes.next()) {
                    showtimeSelector.addItem(rsShowtimes.getString("show_time"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load showtimes.");
        }
    }

    // Method to handle seat reservation
    private void reserveSeat(JFrame mainWindow) {
        String seat = seatSelector.getText();
        String selectedMovie = (String) movieSelector.getSelectedItem();
        String selectedShowtime = (String) showtimeSelector.getSelectedItem();

        if (selectedShowtime == null) {
            JOptionPane.showMessageDialog(null, "Please select a showtime.");
            return;
        }

        // Assume you have a method to get showtime_id based on selectedShowtime
        int showtimeId = getShowtimeId(selectedShowtime);

        if (showtimeId == -1) {
            JOptionPane.showMessageDialog(null, "Invalid showtime selected.");
            return;
        }

        // Validate seat input (e.g., A1, B2, etc.)
        if (seat.length() < 2) {
            JOptionPane.showMessageDialog(null, "Invalid seat format.");
            return;
        }

        String seatRow = seat.substring(0, 1).toUpperCase();
        int seatColumn;
        try {
            seatColumn = Integer.parseInt(seat.substring(1));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid seat number.");
            return;
        }

        // Get personId from session
        int personId = session.getPersonId();

        try (Connection conn = DBConnection.connect()) {
            TicketService ticketService = new TicketService();
            String status = ticketService.reserveTicket(conn, personId, showtimeId, seatRow, seatColumn);
            JOptionPane.showMessageDialog(null, status);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error. Please try again.");
        }
    }

    // Method to get showtime_id based on selectedShowtime
    private int getShowtimeId(String selectedShowtime) {
        // Implement logic to retrieve showtime_id from the database based on selectedShowtime
        try (Connection conn = DBConnection.connect()) {
            String selectedMovie = (String) movieSelector.getSelectedItem();
            String sqlGetMovieId = "SELECT movie_id FROM Movie WHERE title = ?";
            PreparedStatement stmtGetMovieId = conn.prepareStatement(sqlGetMovieId);
            stmtGetMovieId.setString(1, selectedMovie);
            ResultSet rsMovie = stmtGetMovieId.executeQuery();
            if (rsMovie.next()) {
                int movieId = rsMovie.getInt("movie_id");

                String sqlGetShowtimeId = "SELECT showtime_id FROM Showtime WHERE movie_id = ? AND show_time = ?";
                PreparedStatement stmtShowtimeId = conn.prepareStatement(sqlGetShowtimeId);
                stmtShowtimeId.setInt(1, movieId);
                stmtShowtimeId.setString(2, selectedShowtime);
                ResultSet rsShowtime = stmtShowtimeId.executeQuery();
                if (rsShowtime.next()) {
                    return rsShowtime.getInt("showtime_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Indicate error or not found
    }
}
