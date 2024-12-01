package Boundary;

/*
* File Name: Homepage.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 28, 2024
*/

import javax.swing.*;

import Implementation.LoginSession;
import Implementation.RegisteredCustomer;
import Implementation.Theater;
import Implementation.TheaterPopulator;

import java.awt.*;
import java.awt.event.*;

public class Homepage extends JPanel {
    private JLabel theaterTitle;
    private JTextField loginBox;
    private JPasswordField passwordBox;
    private JLabel submitLoginButton;
    private JLabel signUpButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JLabel guestBypassLabel;
    private JLabel developerNote;
    private JLabel background;
    private static final long serialVersionUID = 1L;

    public Homepage(JFrame mainWindow, Theater theater) {

        setLayout(null);
        // Label creation for the homepage
        theaterTitle = new JLabel("ACMEPLEX Theater Portal");
        theaterTitle.setHorizontalAlignment(SwingConstants.CENTER);
        theaterTitle.setForeground(Color.BLACK);
        theaterTitle.setFont(new Font("Calibri", Font.BOLD, 30));
        theaterTitle.setBounds(370, 130, 700, 30);
        add(theaterTitle);

        // Label creation for the username entry field
        loginLabel = new JLabel("Enter your username");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        loginLabel.setBounds(520, 205, 400, 30);
        add(loginLabel);

        // Text field entry creation for username
        loginBox = new JTextField();
        loginBox.setHorizontalAlignment(SwingConstants.CENTER);
        loginBox.setForeground(Color.BLACK);
        loginBox.setBackground(Color.GRAY);
        loginBox.setBounds(520, 230, 400, 30);
        add(loginBox);

        // Label creation for the password text field
        passwordLabel = new JLabel("Enter your password");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        passwordLabel.setBounds(520, 280, 400, 30);
        add(passwordLabel);

        // Password field creation
        passwordBox = new JPasswordField();
        passwordBox.setForeground(Color.BLACK);
        passwordBox.setBackground(Color.GRAY);
        passwordBox.setBounds(520, 305, 400, 30);
        add(passwordBox);

        // Login/submit button creation
        submitLoginButton = new JLabel("Log In");
        submitLoginButton.setHorizontalAlignment(SwingConstants.CENTER);
        submitLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitLoginButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        submitLoginButton.setBounds(520, 340, 400, 30);
        submitLoginButton.setForeground(Color.BLACK);
        add(submitLoginButton);

        // Sign up button creation
        signUpButton = new JLabel("Sign Up");
        signUpButton.setHorizontalAlignment(SwingConstants.CENTER);
        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        signUpButton.setBounds(520, 400, 400, 30);
        signUpButton.setForeground(Color.BLACK);
        add(signUpButton);

        // Guest bypass button for guest users
        guestBypassLabel = new JLabel("Continue Without Signing In");
        guestBypassLabel.setHorizontalAlignment(SwingConstants.CENTER);
        guestBypassLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        guestBypassLabel.setFont(new Font("Calibri", Font.PLAIN, 23));
        guestBypassLabel.setBounds(520, 460, 400, 30);
        guestBypassLabel.setForeground(Color.BLACK);
        add(guestBypassLabel);

        // Developer note creation
        developerNote = new JLabel("Developed by: Arsalan Khaleel, Fahmi Sardar, Zaid Shaikh, Adam Yuen");
        developerNote.setHorizontalAlignment(SwingConstants.CENTER);
        developerNote.setForeground(Color.WHITE);
        developerNote.setFont(new Font("Calibri", Font.PLAIN, 12));
        developerNote.setBounds(520, 530, 400, 30);
        add(developerNote);

        // Set the background image using a JLabel as an image
        background = new JLabel("");
        background.setBounds(100, 0, 1366, 768);
        background.setIcon(new ImageIcon(Homepage.class.getResource("theaterVector.jpg")));
        add(background);

        // Sign up button redirect logic
        signUpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                // Create a bakend session without credentials and create a registration JPanel
                LoginSession guestForNow = new LoginSession(false, null, null, theater);
				Registration registerPanel = new Registration(mainWindow, guestForNow, theater);
				mainWindow.setContentPane(registerPanel);
				mainWindow.revalidate();
			}
		});

        // Continue as guest redirect logic
        guestBypassLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                // Create a bakend session with placeholder credentials and create a browser JPanel
                LoginSession bypassLogin = new LoginSession(false, "Guest", "Guest", theater);
				MovieBrowser registerPanel = new MovieBrowser(mainWindow, bypassLogin, theater);
				mainWindow.setContentPane(registerPanel);
				mainWindow.revalidate();
			}
		});

        // Submit login stuff
        submitLoginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String enteredUsername = loginBox.getText();
                String enteredPassword = String.valueOf(passwordBox.getPassword());

                // If authenticated, create a backend and a browser final

                for (RegisteredCustomer regUser: theater.getUserStorage()) {
                    if (regUser.getEmail().equals(enteredUsername) && regUser.getPassword().equals(enteredPassword)) {
                        LoginSession authenticatedLogin = new LoginSession(true, enteredUsername, enteredPassword, theater);
                        MovieBrowser registerPanel = new MovieBrowser(mainWindow, authenticatedLogin, theater);
                        mainWindow.setContentPane(registerPanel);
                        mainWindow.revalidate();
                        return;
                    }
                    else {
                        continue;
                    }
                }
                JOptionPane.showMessageDialog(null, "Incorrect Credentials");
            }
        });
        setVisible(true);
    }
}
