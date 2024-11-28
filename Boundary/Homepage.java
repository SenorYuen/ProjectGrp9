package Boundary;

import javax.swing.*;
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

    public Homepage(JFrame mainWindow) {
        setLayout(null);
        // Label creation for the homepage
        theaterTitle = new JLabel("I AM NOT RACIST (change title later)");
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
				Registration registerPanel = new Registration(mainWindow);
				mainWindow.setContentPane(registerPanel);
				mainWindow.revalidate();
			}
		});

        // Continue as guest redirect logic
        guestBypassLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MovieBrowser registerPanel = new MovieBrowser(mainWindow);
				mainWindow.setContentPane(registerPanel);
				mainWindow.revalidate();
			}
		});
        setVisible(true);
    }
}
