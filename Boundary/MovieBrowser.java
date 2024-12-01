package Boundary;

/*
* File Name: MovieBrowser.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 28, 2024
*/

import Implementation.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class MovieBrowser extends JPanel{
    // Declaration of GUI components
    private JLabel header;
    private JLabel backButton;
    private Vector<String> tentativeMovieList;
    private Vector<String> showtimeList;
    private JComboBox<String> movieSelector;
    private JPanel seatGrid;
    private JTextField seatSelector;
    private JLabel submitSeatSelection;
    private JLabel currentTheater;
    private JComboBox<String> showtimeSelector;
    private JLabel showtimeLabel;
    private JLabel cancelTicketLabel;
    private JLabel developerNote;
    private JLabel annualFeeLabel;

    // Constrcutor to create the primary JPanel for booking tickets and whatnot
    MovieBrowser(JFrame mainWindow, LoginSession backendConnector, Theater theater) {
        tentativeMovieList = new Vector<>(backendConnector.getMovieNames());
        showtimeList = new Vector<>(); // Initialize showtimeList
        showtimeList.add(theater.getCatalog().get(0).getShowtime().toString());

        setLayout(null);
        // Label creation for the homepage
        header = new JLabel("Browse Movies");
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Calibri", Font.BOLD, 30));
        header.setBounds(515, 20, 400, 30);
        add(header);

        // Creating a back button
        backButton = new JLabel("Return Home");
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        backButton.setBounds(28, 20, 200, 30);
        backButton.setForeground(Color.BLACK);
        add(backButton);

        // Creating a ticket cancellation button
        cancelTicketLabel = new JLabel("Cancel Ticket");
        cancelTicketLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cancelTicketLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelTicketLabel.setFont(new Font("Calibri", Font.PLAIN, 23));
        cancelTicketLabel.setBounds(1100, 20, 200, 30);
        cancelTicketLabel.setForeground(Color.BLACK);
        add(cancelTicketLabel);

        // Creating a membership payment field - only appears for registered users.
        if (backendConnector.getAuthenticationStatus()) {
            annualFeeLabel = new JLabel("Pay Annual membership Fee");
            annualFeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            annualFeeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            annualFeeLabel.setFont(new Font("Calibri", Font.PLAIN, 23));
            annualFeeLabel.setBounds(250, 20, 300, 30);
            annualFeeLabel.setForeground(Color.BLACK);
            // Add listener to invoke a payment recieved notification for registered users
            annualFeeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    LocalDate afterYear = backendConnector.getAccountCreationDate().plusYears(1);
                    if (LocalDate.now().isAfter(afterYear)) {
                        JOptionPane.showMessageDialog(null, "Payment Received, Thank You!");
                    }
                    JOptionPane.showMessageDialog(null, "Payment already completed! Due on: " + afterYear.toString());
                }
            });
            add(annualFeeLabel);
        }

        // Current theater label creation - hardcoded for single theater implementation
        currentTheater = new JLabel("Current Theater: ICT 028");
        currentTheater.setHorizontalAlignment(SwingConstants.CENTER);
        currentTheater.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        currentTheater.setFont(new Font("Calibri", Font.PLAIN, 23));
        currentTheater.setBounds(515, 40, 400, 30);
        currentTheater.setForeground(Color.BLACK);
        add(currentTheater);

        // Showtime label
        showtimeLabel = new JLabel("Select a showtime: ");
        showtimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        showtimeLabel.setFont(new Font("Calibri", Font.PLAIN, 23));
        showtimeLabel.setBounds(-20, 100, 400, 30);
        showtimeLabel.setForeground(Color.BLACK);
        add(showtimeLabel);

        // Creating the movie dropdown menu
        movieSelector = new JComboBox<String>(tentativeMovieList);
        movieSelector.setFont(new Font("Calibri", Font.PLAIN, 20));
        movieSelector.setBackground(Color.GRAY);
        movieSelector.setBounds(515, 140, 400, 30);


        // Logic for movie selector dropdown - rerenders the seat map for each new selection
        movieSelector.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
                // Clear current movie list to add the new showtimes for the specific movie
                showtimeList.clear();

                String selectedMovie = (String) movieSelector.getSelectedItem();
                // If the user is a RU, they get both the unreleased and released catalog of movies.
                if (backendConnector.getAuthenticationStatus()) {
                    // Iterate through the theater's catalogs to create a single one.
                    for (Movie movie: theater.getUnreleasedCatalog()) {
                        if (movie.getName() == selectedMovie) {
                            showtimeList.add(movie.getShowtime().toString());
                        }
                    }
                    for (Movie movie: theater.getCatalog()) {
                        if (movie.getName() == selectedMovie) {
                            showtimeList.add(movie.getShowtime().toString());
                        }
                    }
                }
                // If they are not a RU, they get only the normal seatmap
                else {
                    for (Movie movie: theater.getCatalog()){
                        if (movie.getName() == selectedMovie) {
                            showtimeList.add(movie.getShowtime().toString());
                        }
                    }
                }
                // Rerender the showtime selector to reflect the new showtimes of a selected movie
                remove(showtimeSelector);
                showtimeSelector = new JComboBox<String>(showtimeList);
                showtimeSelector.setFont(new Font("Calibri", Font.PLAIN, 20));
                showtimeSelector.setBackground(Color.GRAY);
                showtimeSelector.setBounds(85, 140, 200, 30);
                add(showtimeSelector);

                seatGrid(theater, selectedMovie);
			}
        });
        add(movieSelector);

        // Construct a new seatgrid - shows available seats for the current movies
        String selectedMovie = (String) movieSelector.getSelectedItem();
        seatGrid(theater, selectedMovie);

        // Render a new dropdown for the showtimes of the currently selected movie.
        showtimeSelector = new JComboBox<String>(showtimeList);
        showtimeSelector.setFont(new Font("Calibri", Font.PLAIN, 20));
        showtimeSelector.setBackground(Color.GRAY);
        showtimeSelector.setBounds(85, 140, 200, 30);

        add(showtimeSelector);


        // Seat selector field
        seatSelector = new JTextField();
        seatSelector.setHorizontalAlignment(SwingConstants.CENTER);
        seatSelector.setForeground(Color.BLACK);
        seatSelector.setBackground(Color.GRAY);
        seatSelector.setBounds(515, 610, 400, 30);
        add(seatSelector);

        // Seat selection submission button
        submitSeatSelection = new JLabel("Submit seat selection");
        submitSeatSelection.setHorizontalAlignment(SwingConstants.CENTER);
        submitSeatSelection.setForeground(Color.BLACK);
        submitSeatSelection.setFont(new Font("Calibri", Font.PLAIN, 23));
        submitSeatSelection.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitSeatSelection.setBounds(515, 640, 400, 30);
        add(submitSeatSelection);

        // Creation of a developer note
        developerNote = new JLabel("Developed by: Arsalan Khaleel, Fahmi Sardar, Zaid Shaikh, Adam Yuen");
        developerNote.setHorizontalAlignment(SwingConstants.CENTER);
        developerNote.setForeground(Color.BLACK);
        developerNote.setFont(new Font("Calibri", Font.PLAIN, 12));
        developerNote.setBounds(520, 700, 400, 30);
        add(developerNote);

        // Add back button functionality
        backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                // change the current contentpane to the homepage to emulate going back.
				Homepage homepage = new Homepage(mainWindow, theater);
				mainWindow.setContentPane(homepage);
				mainWindow.revalidate();
			}
		});

        // Logic to cancel a ticket
        cancelTicketLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Prompt the user to enter their ticket number
                String ticketNumber = JOptionPane.showInputDialog("Enter Receipt Number to Cancel:");

                // Check if the input is null or invalid
                if (ticketNumber == null) {
                    JOptionPane.showMessageDialog(null, "Action canceled.");
                    return;
                }

                // Determine the validity of the ticket number and handle cancellation logic
                try {
                    int ticketNum = Integer.parseInt(ticketNumber);

                    if (ticketNum <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid Receipt Number. Please enter a valid number.");
                    } else {
                        // If a ticket is valid then it will be cancelled
                        // Add refund percentage based on date or proceed with cancellation
                        for (Receipt currReceipt: theater.getReceiptStorage()) {
                            if (Integer.parseInt(ticketNumber) == currReceipt.getId()) {
                                Date movieShowtime;
                                LocalDate expired = LocalDate.now();
                                String curMovie = currReceipt.getMovieName();

                                for (int i = 0; i < theater.getCatalog().size(); i++) {
                                    if (theater.getCatalog().get(i).getName().equals(selectedMovie)) {
                                        theater.getCatalog().get(i).getSeatMap().get(Integer.valueOf(currReceipt.getSeatNumber()) - 1).setTaken(false);
                                        break; // Exit the loop early once the movie is found
                                    }
                                }
                                for (int i = 0; i < theater.getUnreleasedCatalog().size(); i++) {
                                    if (theater.getUnreleasedCatalog().get(i).getName().equals(selectedMovie)) {
                                        theater.getUnreleasedCatalog().get(i).getSeatMap().get(Integer.valueOf(currReceipt.getSeatNumber()) - 1).setTaken(false);
                                        break; // Exit the loop early once the movie is found
                                    }
                                }
                                seatGrid(theater, selectedMovie);

                                for (Movie movie: theater.getCatalog()) {
                                    if (movie.getName().equals(curMovie)) {
                                        movieShowtime = movie.getShowtime();
                                        expired = LocalDate.of(movieShowtime.getYear(), movieShowtime.getMonth(), movieShowtime.getDay());
                                    }
                                }
                                for (Movie movie: theater.getUnreleasedCatalog()) {
                                    if (movie.getName().equals(curMovie)) {
                                        movieShowtime = movie.getShowtime();
                                        expired = LocalDate.of(movieShowtime.getYear(), movieShowtime.getMonth(), movieShowtime.getDay());
                                    }
                                }

                                if (LocalDate.now().isBefore(expired)) {
                                    if (backendConnector.getAuthenticationStatus()) {
                                        JOptionPane.showMessageDialog(null, "Ticket " + ticketNum + " cancelled. Received 100% refund");
                                        theater.getReceiptStorage().remove(currReceipt);
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(null, "Ticket " + ticketNum + " cancelled. Received 85% refund");
                                        theater.getReceiptStorage().remove(currReceipt);
                                    }
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "Ticket " + ticketNum + " is not refundable");
                                }
                            }
                        }

                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric ticket number.");
                }
            }
        });

        // Handle the logic for handling seat reservation.
        submitSeatSelection.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int choice = JOptionPane.NO_OPTION;
                String seat = seatSelector.getText();
                String selectedMovie = (String) movieSelector.getSelectedItem();

                int numberOfOccupiedSeats = 0;

                // Determine the number of occupied seats for a given movie
                for (Movie movie: theater.getCatalog()) {
                    if (movie.getName().equals(selectedMovie)) {
                        for (Seat currentSeat: theater.getCatalog().get(0).getSeatMap()) {
                            if (currentSeat.getTaken()) {
                                numberOfOccupiedSeats++;
                            }
                        }
                    }
                }

                // If a movie is in the uncreleased catalog then restrict 90% of the seats
                for (Movie movie: theater.getUnreleasedCatalog()) {
                    if (movie.getName().equals(selectedMovie) && numberOfOccupiedSeats > 2) {
                        JOptionPane.showMessageDialog(null, "10% of seats for this unreleased movie have already been booked.");
                        return;
                    }
                }

                // Enusre that a seat is valid.
                try {
                    int seatNum = Integer.parseInt(seat);

                    if (seatNum <= 0 || seatNum > 20) {
                        JOptionPane.showMessageDialog(null, "Invalid Ticket Number. Please enter a valid number.");
                    } else {
                        // logic for seat being available.
                        choice = JOptionPane.showConfirmDialog(null, "Purchase ticket for seat " + seat + "?",
				        "Confirm Purchase?\nTotal cost: $90", JOptionPane.YES_NO_CANCEL_OPTION);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric seat number.");
                }

            // If the user confirms their payment and is not a RU.
            if (choice == JOptionPane.YES_OPTION && !backendConnector.getAuthenticationStatus()) {
                // Begin payment option logic
                String cardSelection = JOptionPane.showInputDialog("Enter Card Number");
                boolean cardNumberValid = false;

                // Determine validity of credid card info - assume link to banking services.
                try {
                    Integer.parseInt(cardSelection);
                    cardNumberValid = true;}
                catch(NumberFormatException a) {
                    JOptionPane.showMessageDialog(null, "Invalid Payment Info, please try again");
                }

                // Assuming the banking info was valid, then we begin creation of all payment related objects.
                if (cardNumberValid) {
                    String movieName = selectedMovie;
                    String theaterLocation = theater.getLocation();
                    LocalDate currentDate = LocalDate.now();
                    Double ticketCost = 90.0;

                    // Set the selected seat status to occupied.
                    int index = -1; // Initialize index variable
                    for (int i = 0; i < theater.getCatalog().size(); i++) {
                        if (theater.getCatalog().get(i).getName().equals(selectedMovie)) {
                            index = i; // Store the index when a match is found
                            break; // Exit the loop early once the movie is found
                        }
                    }
                    theater.getCatalog().get(index).getSeatMap().get(Integer.valueOf(seat) - 1).setTaken(true);

                    seatGrid(theater, selectedMovie);

                    // Make Ticket --> Seat number (infobox), movie name (theater login object), theater location (theater login object)
                    Ticket userTicket = new Ticket(seat, movieName, theaterLocation);

                    // Make Payment --> paymentDate (make current date, make string), payment amount = $90, card number, take prev ticket.
                    Payment userPayment = new Payment(currentDate, ticketCost, cardSelection, userTicket);

                    // Receipt --> amount = 90, from prev, from before, from ui.
                    Receipt userReceipt = new Receipt(ticketCost, currentDate, movieName, seat);
                    theater.getReceiptStorage().add(userReceipt);

                    // make ui thing display shit, destory objects. revaldiate page. done.
                    JOptionPane.showMessageDialog(
                        null,
                        "Payment Successful. A copy of this receipt was emailed to you." +
                        "\n\nReceipt ID: " + String.valueOf(userReceipt.getId()) +
                        "\nMovie: " + movieName +
                        "\nSeat: " + seat +
                        "\nPrice: $" + ticketCost +
                        "\nCard Number: " + cardSelection +
                        "\nDate: " + currentDate.toString()
                        );
                }


            // If the user confirms their ticket and is a RU, begin creating the payment related objects.
            } else if (choice == JOptionPane.YES_OPTION && backendConnector.getAuthenticationStatus()) {
                String movieName = selectedMovie;
                String theaterLocation = theater.getLocation();
                LocalDate currentDate = LocalDate.now();
                Double ticketCost = 90.0;

                int index = -1; // Initialize index variable
                for (int i = 0; i < theater.getCatalog().size(); i++) {
                    if (theater.getCatalog().get(i).getName().equals(selectedMovie)) {
                        index = i; // Store the index when a match is found
                        theater.getCatalog().get(index).getSeatMap().get(Integer.valueOf(seat) - 1).setTaken(true);
                        break; // Exit the loop early once the movie is found
                    }
                }

                for (int i = 0; i < theater.getUnreleasedCatalog().size(); i++) {
                    if (theater.getUnreleasedCatalog().get(i).getName().equals(selectedMovie)) {
                        index = i; // Store the index when a match is found
                        theater.getUnreleasedCatalog().get(index).getSeatMap().get(Integer.valueOf(seat) - 1).setTaken(true);
                        break; // Exit the loop early once the movie is found
                    }
                }
                seatGrid(theater, selectedMovie);

                // Make Ticket --> Seat number (infobox), movie name (theater login object), theater location (theater login object)
                Ticket userTicket = new Ticket(seat, movieName, theaterLocation);

                // Make Payment --> paymentDate (make current date, make string), payment amount = $90, card number, take prev ticket.
                Payment userPayment = new Payment(currentDate, ticketCost, backendConnector.getCard(), userTicket);

                // Receipt --> amount = 90, from prev, from before, from ui.
                Receipt userReceipt = new Receipt(ticketCost, currentDate, movieName, seat);
                theater.getReceiptStorage().add(userReceipt);

                // make ui thing display stuff, destroy objects. revaldiate page. done.
                JOptionPane.showMessageDialog(
                    null,
                    "Payment Successful. A copy of this receipt was emailed to you." +
                    "\n\nReceipt ID: " + String.valueOf(userReceipt.getId()) +
                    "\nMovie: " + movieName +
                    "\nSeat: " + seat +
                    "\nPrice: $" + ticketCost +
                    "\nCard Number: " + backendConnector.getCard() +
                    "\nDate: " + currentDate.toString()
                );
            } else if (choice == JOptionPane.NO_OPTION) {
                // If the user chose 'No', show a message indicating that changes are not saved
                JOptionPane.showMessageDialog(null, "Purchase cancelled. You will not be charged");
            } else {
                // If the user chose 'Cancel' or closed the dialog, show a message indicating the operation is canceled
                JOptionPane.showMessageDialog(null, "Transaction canceled.");
            }
            }
        });

        setVisible(true);
    }

    // Function for creating and rendering a seat map.
    public void seatGrid(Theater theater, String selectedMovie) {
        // Creating the seat grid
        seatGrid = new JPanel(new GridLayout(5, 4, 5, 5));
        seatGrid.setBounds(515, 190, 400, 400);

        seatGrid.removeAll(); // Clear existing components

        // Find the selected movie from the catalog
        for (Movie movie : theater.getCatalog()) {
            if (movie.getName().equals(selectedMovie)) {
                ArrayList<Seat> seatMap = movie.getSeatMap();

                for (int i = 0; i < seatMap.size(); i++) {
                    JTextField seatText = new JTextField("Seat " + (i + 1));
                    seatText.setBackground(Color.GREEN);
                    seatText.setHorizontalAlignment(SwingConstants.CENTER);

                    // Check if the seat is taken
                    if (seatMap.get(i).getTaken()) {
                        seatText.setBackground(Color.RED);
                    }

                    seatGrid.add(seatText); // Add to the grid
                }
            }
        }
        for (Movie movie : theater.getUnreleasedCatalog()) {
            if (movie.getName().equals(selectedMovie)) {
                ArrayList<Seat> seatMap = movie.getSeatMap();

                for (int i = 0; i < seatMap.size(); i++) {
                    JTextField seatText = new JTextField("Seat " + (i + 1));
                    seatText.setBackground(Color.GREEN);
                    seatText.setHorizontalAlignment(SwingConstants.CENTER);

                    // Check if the seat is taken
                    if (seatMap.get(i).getTaken()) {
                        seatText.setBackground(Color.RED);
                    }

                    seatGrid.add(seatText); // Add to the grid
                }
            }
        }

        // Revalidate and repaint the grid
        seatGrid.revalidate();
        seatGrid.repaint();
        add(seatGrid); // Add the updated grid to the container
    }
}