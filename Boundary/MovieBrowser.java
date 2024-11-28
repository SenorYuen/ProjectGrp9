package Boundary;

import Implementation.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class MovieBrowser extends JPanel{
    private JLabel header;
    private JLabel backButton;
    private Vector<String> tentativeMovieList = new Vector<String>(1);
    private JComboBox<String> movieSelector;
    private JPanel seatGrid;
    private JTextField seatSelector;
    private JLabel submitSeatSelection;
    private JLabel currentTheater;
    private JComboBox<String> showtimeSelector;
    private JLabel showtimeLabel;
    private JLabel cancelTicketLabel;
    private JLabel developerNote;

    MovieBrowser(JFrame mainWindow) {
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
        tentativeMovieList.addElement("Finding Fahmi");
        tentativeMovieList.addElement("Finding Fahmi 2");
        tentativeMovieList.addElement("Odin's Downfall");
        movieSelector = new JComboBox<String>(tentativeMovieList);
        movieSelector.setFont(new Font("Calibri", Font.PLAIN, 20));
        movieSelector.setBackground(Color.GRAY);
        movieSelector.setBounds(515, 140, 400, 30);
        add(movieSelector);

        // Creating the seat grid
        seatGrid = new JPanel(new GridLayout(5, 4, 5, 5));
        seatGrid.setBounds(515, 190, 400, 400);
        for (int i = 0; i < 20; i++) {
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
            // Prompt the user to enter their article name and store it in the 'name' variable
            String name = JOptionPane.showInputDialog("Enter Ticket Number to Cancel: ");
                    
            // Create a JOptionPane to display a message with a personalized greeting
            JOptionPane.showMessageDialog(null, "Cancelled or not, depending on time");
			}
		});

        submitSeatSelection.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "wtf",
				"Confirm Purchase?", JOptionPane.YES_NO_CANCEL_OPTION);

            // Check the user's choice and display a corresponding message
            if (choice == JOptionPane.YES_OPTION) {
                // If the user chose 'Yes', show a message indicating that changes are saved
                JOptionPane.showMessageDialog(null, "Changes saved.");
            } else if (choice == JOptionPane.NO_OPTION) {
                // If the user chose 'No', show a message indicating that changes are not saved
                JOptionPane.showMessageDialog(null, "Changes not saved.");
            } else {
                // If the user chose 'Cancel' or closed the dialog, show a message indicating the operation is canceled
                JOptionPane.showMessageDialog(null, "Operation canceled.");
            }
            }
        });
        

        setVisible(true);
    }
}
