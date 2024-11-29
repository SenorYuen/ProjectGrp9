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
import java.util.Vector;

public class MovieBrowser extends JPanel{
    private JLabel header;
    private JLabel backButton;
    private Vector<String> tentativeMovieList;
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

    MovieBrowser(JFrame mainWindow, LoginSession backendConnector) {
        Vector<String> vector = new Vector<>(backendConnector.getMovieNames());
        tentativeMovieList = vector;

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

        // Creating a membership payment field
        if (backendConnector.getAuthenticationStatus()) {
            annualFeeLabel = new JLabel("Pay Annual membership Fee");
            annualFeeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            annualFeeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            annualFeeLabel.setFont(new Font("Calibri", Font.PLAIN, 23));
            annualFeeLabel.setBounds(250, 20, 300, 30);
            annualFeeLabel.setForeground(Color.BLACK);
            annualFeeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(null, "Payment Received, Thank You! (add date time functionality later))");
                }
            });
            add(annualFeeLabel);
        }

        // Current theater label creation
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

        // Dropdown for showtimes:
        showtimeSelector = new JComboBox<String>(tentativeMovieList);
        showtimeSelector.setFont(new Font("Calibri", Font.PLAIN, 20));
        showtimeSelector.setBackground(Color.GRAY);
        showtimeSelector.setBounds(85, 140, 200, 30);
        add(showtimeSelector);


        // Creating the movie dropdown menu
        movieSelector = new JComboBox<String>(tentativeMovieList);
        movieSelector.setFont(new Font("Calibri", Font.PLAIN, 20));
        movieSelector.setBackground(Color.GRAY);
        movieSelector.setBounds(515, 140, 400, 30);
        add(movieSelector);

        // Creating the seat grid
        seatGrid = new JPanel(new GridLayout(5, 4, 5, 5));
        seatGrid.setBounds(515, 190, 400, 400);
        for (int i = 0; i < 20; i++) {
            // needs theater accessing.
            JTextField seatText = new JTextField("Seat " + (i+1) + ":  Free");
            seatGrid.add(seatText, BorderLayout.CENTER);
        }
        add(seatGrid);

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
				Homepage homepage = new Homepage(mainWindow);
				mainWindow.setContentPane(homepage);
				mainWindow.revalidate();
			}
		});

        cancelTicketLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Prompt the user to enter their ticket number
                String ticketNumber = JOptionPane.showInputDialog("Enter Ticket Number to Cancel:");

                // Check if the input is null or invalid
                if (ticketNumber == null) {
                    JOptionPane.showMessageDialog(null, "Action canceled.");
                    return;
                }

                try {
                    int ticketNum = Integer.parseInt(ticketNumber);

                    if (ticketNum <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid Ticket Number. Please enter a valid number.");
                    } else {
                        // Add refund percentage based on date or proceed with cancellation
                        JOptionPane.showMessageDialog(null, "Ticket " + ticketNum + " cancelled.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a numeric ticket number.");
                }
            }
        });

        submitSeatSelection.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int choice = JOptionPane.NO_OPTION;
                String seat = seatSelector.getText();

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

            // Check the user's choice and display a corresponding message
            if (choice == JOptionPane.YES_OPTION && !backendConnector.getAuthenticationStatus()) {
                // If the user chose 'Yes', show a message indicating that changes are saved
                String cardSelection = JOptionPane.showInputDialog("Enter Card Number");
                boolean cardNumberValid = false;
                try {
                    Integer.parseInt(cardSelection);
                    cardNumberValid = true;}
                catch(NumberFormatException a) {
                    JOptionPane.showMessageDialog(null, "Invalid Payment Info, please try again");
                }

                if (cardNumberValid) {
                    String movieName = "grab from theater instance";
                    String theaterLocation = "grab from theater";
                    String currentDate = LocalDate.now().toString();
                    Double ticketCost = 90.0;

                    // Make Ticket --> Seat number (infobox), movie name (theater login object), theater location (theater login object)
                    Ticket userTicket = new Ticket(seat, movieName, theaterLocation);

                    // Make Payment --> paymentDate (make current date, make string), payment amount = $90, card number, take prev ticket.
                    Payment userPayment = new Payment(currentDate, ticketCost, cardSelection, userTicket);

                    // Receipt --> amount = 90, from prev, from before, from ui.
                    Receipt userReceipt = new Receipt(ticketCost, currentDate, movieName, seat);

                    // make ui thing display shit, destory objects. revaldiate page. done.
                    JOptionPane.showMessageDialog(
                        null,
                        "Payment Successful. A copy of this receipt was emailed to <email from login>" +
                        "\n\nReceipt ID: " + String.valueOf(userReceipt.getId()) +
                        "\nMovie: " + movieName +
                        "\nSeat: " + seat +
                        "\nPrice: $" + ticketCost +
                        "\nCard Number: " + cardSelection
                        );
                }
            } else if (choice == JOptionPane.YES_OPTION && backendConnector.getAuthenticationStatus()) {
                String movieName = "grab from theater instance";
                String theaterLocation = "grab from theater";
                String currentDate = LocalDate.now().toString();
                Double ticketCost = 90.0;

                // Make Ticket --> Seat number (infobox), movie name (theater login object), theater location (theater login object)
                Ticket userTicket = new Ticket(seat, movieName, theaterLocation);

                // Make Payment --> paymentDate (make current date, make string), payment amount = $90, card number, take prev ticket.
                Payment userPayment = new Payment(currentDate, ticketCost, "PLACEHOLDER NUMBER", userTicket);

                // Receipt --> amount = 90, from prev, from before, from ui.
                Receipt userReceipt = new Receipt(ticketCost, currentDate, movieName, seat);

                // make ui thing display shit, destory objects. revaldiate page. done.
                JOptionPane.showMessageDialog(
                    null,
                    "Payment Successful. A copy of this receipt was emailed to <email from login>" +
                    "\n\nReceipt ID: " + String.valueOf(userReceipt.getId()) +
                    "\nMovie: " + movieName +
                    "\nSeat: " + seat +
                    "\nPrice: $" + ticketCost +
                    "\nCard Number: " + "PLACEHOLDER NUMBER"
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
}
