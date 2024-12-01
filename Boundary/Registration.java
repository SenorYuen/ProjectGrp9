package Boundary;

import util.RegisterCustomer;
import util.DBConnection;
import util.LoginSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

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
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel addressLabel;
    private JTextField addressField;

    private static final long serialVersionUID = 1L;

    private JFrame mainWindow;
    private LoginSession backendConnector;

    public Registration(JFrame mainWindow, LoginSession backendConnector) {
        this.mainWindow = mainWindow;
        this.backendConnector = backendConnector;

        setLayout(null);
        // Label creation for the registration page
        header = new JLabel("Registration Page");
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Calibri", Font.BOLD, 30));
        header.setBounds(520, 50, 400, 30);
        add(header);

        // Label creation for the name entry field
        nameLabel = new JLabel("Enter full name");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        nameLabel.setBounds(520, 100, 400, 30);
        add(nameLabel);

        // Text field entry creation for name
        nameField = new JTextField();
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setForeground(Color.BLACK);
        nameField.setBackground(Color.GRAY);
        nameField.setBounds(520, 125, 400, 30);
        add(nameField);

        // Label creation for the email entry field
        emailLabel = new JLabel("Enter your email");
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        emailLabel.setBounds(520, 170, 400, 30);
        add(emailLabel);

        // Text field entry creation for email
        emailBox = new JTextField();
        emailBox.setHorizontalAlignment(SwingConstants.CENTER);
        emailBox.setForeground(Color.BLACK);
        emailBox.setBackground(Color.GRAY);
        emailBox.setBounds(520, 195, 400, 30);
        add(emailBox);

        // Label creation for the password text field
        passwordLabel = new JLabel("Enter your password");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        passwordLabel.setBounds(520, 240, 400, 30);
        add(passwordLabel);

        // Password field creation
        passwordBox = new JPasswordField();
        passwordBox.setForeground(Color.BLACK);
        passwordBox.setBackground(Color.GRAY);
        passwordBox.setBounds(520, 265, 400, 30);
        add(passwordBox);

        // Label creation for the confirm password text field
        confirmPasswordLabel = new JLabel("Confirm your password");
        confirmPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmPasswordLabel.setForeground(Color.BLACK);
        confirmPasswordLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        confirmPasswordLabel.setBounds(520, 310, 400, 30);
        add(confirmPasswordLabel);

        // Password field creation
        confirmPasswordBox = new JPasswordField();
        confirmPasswordBox.setForeground(Color.BLACK);
        confirmPasswordBox.setBackground(Color.GRAY);
        confirmPasswordBox.setBounds(520, 335, 400, 30);
        add(confirmPasswordBox);

        // Address Label
        addressLabel = new JLabel("Enter your address");
        addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addressLabel.setForeground(Color.BLACK);
        addressLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        addressLabel.setBounds(520, 380, 400, 30);
        add(addressLabel);

        // Address Field creation
        addressField = new JTextField();
        addressField.setHorizontalAlignment(SwingConstants.CENTER);
        addressField.setForeground(Color.BLACK);
        addressField.setBackground(Color.GRAY);
        addressField.setBounds(520, 405, 400, 30);
        add(addressField);

        // Card Info Label
        cardLabel = new JLabel("Enter Card Info");
        cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardLabel.setForeground(Color.BLACK);
        cardLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        cardLabel.setBounds(520, 450, 400, 30);
        add(cardLabel);

        // Card Field creation
        cardField = new JTextField();
        cardField.setHorizontalAlignment(SwingConstants.CENTER);
        cardField.setForeground(Color.BLACK);
        cardField.setBackground(Color.GRAY);
        cardField.setBounds(520, 475, 400, 30);
        add(cardField);

        // Sign up button creation
        signUpButton = new JLabel("Sign Up");
        signUpButton.setHorizontalAlignment(SwingConstants.CENTER);
        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        signUpButton.setBounds(520, 520, 400, 30);
        signUpButton.setForeground(Color.BLACK);
        add(signUpButton);

        // Back button creation
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

        // Backbutton to render a homepage jpanel to emulate return home functionality
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Homepage homepage = new Homepage(mainWindow);
                mainWindow.setContentPane(homepage);
                mainWindow.revalidate();
            }
        });

        // Logic to handle registration
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Grab all of the data from the text fields
                String name = nameField.getText();
                String email = emailBox.getText();
                String passwordInitial = String.valueOf(passwordBox.getPassword());
                String passwordFinal = String.valueOf(confirmPasswordBox.getPassword());
                String cardNumber = cardField.getText();
                String address = addressField.getText();

                // Ensure password match
                if (!passwordInitial.equals(passwordFinal)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                    return;
                }

                // Register the customer
                try (Connection conn = DBConnection.connect()) {
                    RegisterCustomer registerCustomer = new RegisterCustomer();
                    String status = registerCustomer.registerNewCustomer(conn, name, email, passwordFinal, address, cardNumber);
                    JOptionPane.showMessageDialog(null, status);

                    if (status.equals("Registration Successful!")) {
                        // Redirect to homepage or login
                        Homepage homepage = new Homepage(mainWindow);
                        mainWindow.setContentPane(homepage);
                        mainWindow.revalidate();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database connection failed: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }
}
