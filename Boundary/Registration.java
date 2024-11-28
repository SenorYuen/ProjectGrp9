package Boundary;

/*
* File Name: Registration.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 28, 2024
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Registration extends JPanel {
    private JLabel header;
    private JLabel emailLabel;
    private JTextField emailBox;
    private JLabel passwordLabel;
    private JPasswordField passwordBox;
    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordBox;
    private JLabel signUpButton;
    private JLabel backButton;
    private JLabel developerNote;
    private JLabel cardLabel;
    private JTextField cardField;

    private static final long serialVersionUID = 1L;

    public Registration(JFrame mainWindow) {
        setLayout(null);
        // Creating a label for the page title
        header = new JLabel("Registration Page");
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Calibri", Font.BOLD, 30));
        header.setBounds(520, 50, 400, 30);
        add(header);

        // Label creation for the username entry field
        emailLabel = new JLabel("Enter your email");
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        emailLabel.setBounds(520, 125, 400, 30);
        add(emailLabel);

        // Text field entry creation for username/email
        emailBox = new JTextField();
        emailBox.setHorizontalAlignment(SwingConstants.CENTER);
        emailBox.setForeground(Color.BLACK);
        emailBox.setBackground(Color.GRAY);
        emailBox.setBounds(520, 150, 400, 30);
        add(emailBox);

        // Label creation for the password text field
        passwordLabel = new JLabel("Enter your password");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        passwordLabel.setBounds(520, 200, 400, 30);
        add(passwordLabel);

        // Password field creation
        passwordBox = new JPasswordField();
        passwordBox.setForeground(Color.BLACK);
        passwordBox.setBackground(Color.GRAY);
        passwordBox.setBounds(520, 225, 400, 30);
        add(passwordBox);

        // Label creation for the password text field
        confirmPasswordLabel = new JLabel("Confirm your password");
        confirmPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmPasswordLabel.setForeground(Color.BLACK);
        confirmPasswordLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        confirmPasswordLabel.setBounds(520, 275, 400, 30);
        add(confirmPasswordLabel);

        // Password field creation
        confirmPasswordBox = new JPasswordField();
        confirmPasswordBox.setForeground(Color.BLACK);
        confirmPasswordBox.setBackground(Color.GRAY);
        confirmPasswordBox.setBounds(520, 300, 400, 30);
        add(confirmPasswordBox);

        // Sign up button creation
        signUpButton = new JLabel("Sign Up");
        signUpButton.setHorizontalAlignment(SwingConstants.CENTER);
        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        signUpButton.setBounds(520, 425, 400, 30);
        signUpButton.setForeground(Color.BLACK);
        add(signUpButton);

        // Card Info Label
        cardLabel = new JLabel("Enter Card Info");
        cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardLabel.setForeground(Color.BLACK);
        cardLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        cardLabel.setBounds(520, 350, 400, 30);
        add(cardLabel);

        // Card Field creation:
        cardField = new JTextField();
        cardField.setHorizontalAlignment(SwingConstants.CENTER);
        cardField.setForeground(Color.BLACK);
        cardField.setBackground(Color.GRAY);
        cardField.setBounds(520, 375, 400, 30);
        add(cardField);

        // Sign up button creation
        backButton = new JLabel("Return Home");
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        backButton.setBounds(20, 20, 200, 30);
        backButton.setForeground(Color.BLACK);
        add(backButton);

        // Creation of a developer note
        developerNote = new JLabel("Developed by: Arsalan Khaleel, Fahmi Sardar, Zaid Shaikh, Adam Yuen");
        developerNote.setHorizontalAlignment(SwingConstants.CENTER);
        developerNote.setForeground(Color.BLACK);
        developerNote.setFont(new Font("Calibri", Font.PLAIN, 12));
        developerNote.setBounds(520, 650, 400, 30);
        add(developerNote);

        backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Homepage homepage = new Homepage(mainWindow);
				mainWindow.setContentPane(homepage);
				mainWindow.revalidate();
			}
		});

        setVisible(true);
    }
    
}
