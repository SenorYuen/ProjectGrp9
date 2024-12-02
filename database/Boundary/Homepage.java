package Boundary;

import javax.swing.*;
import util.LoginSession;
import util.Login;
import util.DBConnection;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Homepage extends JPanel {
    // Declaration of GUI components
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

    private JFrame mainWindow;

    public Homepage(JFrame mainWindow) {
        this.mainWindow = mainWindow;
        setLayout(null);

        // Initialize and add components
        theaterTitle = new JLabel("Welcome to ACMEPLEX Theater Portal");
        theaterTitle.setHorizontalAlignment(SwingConstants.CENTER);
        theaterTitle.setForeground(Color.BLACK);
        theaterTitle.setFont(new Font("Calibri", Font.BOLD, 30));
        theaterTitle.setBounds(520, 50, 400, 30);
        add(theaterTitle);

        // Login Label
        loginLabel = new JLabel("Email:");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        loginLabel.setBounds(520, 100, 400, 30);
        add(loginLabel);

        // Login Text Field
        loginBox = new JTextField();
        loginBox.setHorizontalAlignment(SwingConstants.CENTER);
        loginBox.setForeground(Color.BLACK);
        loginBox.setBackground(Color.GRAY);
        loginBox.setBounds(520, 130, 400, 30);
        add(loginBox);

        // Password Label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        passwordLabel.setBounds(520, 170, 400, 30);
        add(passwordLabel);

        // Password Field
        passwordBox = new JPasswordField();
        passwordBox.setHorizontalAlignment(SwingConstants.CENTER);
        passwordBox.setForeground(Color.BLACK);
        passwordBox.setBackground(Color.GRAY);
        passwordBox.setBounds(520, 200, 400, 30);
        add(passwordBox);

        // Submit Login Button
        submitLoginButton = new JLabel("Login");
        submitLoginButton.setHorizontalAlignment(SwingConstants.CENTER);
        submitLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitLoginButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        submitLoginButton.setBounds(520, 250, 400, 30);
        submitLoginButton.setForeground(Color.BLACK);
        add(submitLoginButton);

        // Sign Up Button
        signUpButton = new JLabel("Register");
        signUpButton.setHorizontalAlignment(SwingConstants.CENTER);
        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        signUpButton.setBounds(520, 300, 400, 30);
        signUpButton.setForeground(Color.BLACK);
        add(signUpButton);

        // Guest Bypass Label
        guestBypassLabel = new JLabel("Continue as Guest");
        guestBypassLabel.setHorizontalAlignment(SwingConstants.CENTER);
        guestBypassLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        guestBypassLabel.setFont(new Font("Calibri", Font.PLAIN, 23));
        guestBypassLabel.setBounds(520, 350, 400, 30);
        guestBypassLabel.setForeground(Color.BLACK);
        add(guestBypassLabel);

        // Developer Note
        developerNote = new JLabel("Developed by: Arsalan Khaleel, Fahmi Sardar, Zaid Shaikh, Adam Yuen");
        developerNote.setHorizontalAlignment(SwingConstants.CENTER);
        developerNote.setForeground(Color.BLACK);
        developerNote.setFont(new Font("Calibri", Font.PLAIN, 12));
        developerNote.setBounds(520, 650, 400, 30);
        add(developerNote);

        // Add listeners

        // Sign up button listener
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Create a backend session without credentials and create a registration JPanel
                LoginSession guestForNow = new LoginSession();
                guestForNow.setAuthenticated(false);
                guestForNow.setUserType("guest");
                Registration registerPanel = new Registration(mainWindow, guestForNow);
                mainWindow.setContentPane(registerPanel);
                mainWindow.revalidate();
            }
        });

        // Guest Bypass listener
        guestBypassLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Create a backend session with guest credentials and create a browser JPanel
                LoginSession bypassLogin = new LoginSession();
                bypassLogin.setAuthenticated(false);
                bypassLogin.setUserType("guest");
                MovieBrowser browserPanel = new MovieBrowser(mainWindow, bypassLogin);
                mainWindow.setContentPane(browserPanel);
                mainWindow.revalidate();
            }
        });

        // Submit login button listener
        submitLoginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String enteredUsername = loginBox.getText();
                String enteredPassword = String.valueOf(passwordBox.getPassword());

                try {
                    Connection conn = DBConnection.connect();
                    Login login = new Login();
                    LoginSession session = login.authenticateUser(conn, enteredUsername, enteredPassword);

                    if (session.isAuthenticated()) {
                        // Authenticated, proceed to MovieBrowser
                        MovieBrowser browser = new MovieBrowser(mainWindow, session);
                        mainWindow.setContentPane(browser);
                        mainWindow.revalidate();
                    } else {
                        JOptionPane.showMessageDialog(null, "Login failed: " + session.getStatus());
                    }

                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database connection error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }
}