package Boundary;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class Homepage extends JPanel {
    private JLabel theaterTitle;
    private JTextField loginBox;
    private JPasswordField passwordBox;
    private JLabel submitLoginButton;
    private JLabel signUpButton;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private static final long serialVersionUID = 1L;

    public Homepage() {
        // Label creation for the homepage
        theaterTitle = new JLabel("I AM NOT RACIST (change title later)");
        theaterTitle.setHorizontalAlignment(SwingConstants.CENTER);
        theaterTitle.setForeground(Color.BLACK);
        theaterTitle.setFont(new Font("Calibri", Font.BOLD, 30));
        theaterTitle.setBounds(370, 50, 700, 30);
        add(theaterTitle);

        // Label creation for the username entry field
        loginLabel = new JLabel("Enter your username");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        loginLabel.setBounds(520, 125, 350, 30);
        add(loginLabel);

        // Text field entry creation for username
        loginBox = new JTextField();
        loginBox.setHorizontalAlignment(SwingConstants.CENTER);
        loginBox.setForeground(Color.BLACK);
        loginBox.setBackground(Color.GRAY);
        loginBox.setBounds(515, 150, 400, 30);
        add(loginBox);

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
        passwordBox.setBounds(515, 225, 400, 30);
        add(passwordBox);

        // Login/submit button creation
        submitLoginButton = new JLabel("Log In");
        submitLoginButton.setHorizontalAlignment(SwingConstants.CENTER);
        submitLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitLoginButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        submitLoginButton.setBounds(515, 260, 400, 30);
        submitLoginButton.setForeground(Color.BLACK);
        add(submitLoginButton);

        // Sign up button creation
        signUpButton = new JLabel("Sign Up");
        signUpButton.setHorizontalAlignment(SwingConstants.CENTER);
        signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        signUpButton.setBounds(515, 320, 400, 30);
        signUpButton.setForeground(Color.BLACK);
        add(signUpButton);

        setVisible(true);
    }
}
