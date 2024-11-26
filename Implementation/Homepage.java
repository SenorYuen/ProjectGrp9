package Implementation;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class Homepage extends JPanel {
    private JLabel theaterTitle;
    private JTextField loginBox;
    private JPasswordField passwordBox;
    private JLabel submitLoginButton;
    private JLabel signUpButton;
    private static final long serialVersionUID = 1L;

    public Homepage() {
        theaterTitle = new JLabel("ACMEPLEX HOMEPAGE");
        theaterTitle.setHorizontalAlignment(SwingConstants.CENTER);
        theaterTitle.setForeground(Color.BLACK);
        theaterTitle.setFont(new Font("Arial", Font.BOLD, 30));
        theaterTitle.setBounds(515, 200, 350, 30);
        add(theaterTitle);

        loginBox = new JTextField();
        loginBox.setHorizontalAlignment(SwingConstants.CENTER);
        loginBox.setForeground(Color.BLACK);
        loginBox.setBackground(Color.GRAY);
        loginBox.setFont(new Font("Arial", Font.BOLD, 30));
        loginBox.setBounds(515, 400, 350, 30);
        add(loginBox);

        passwordBox = new JPasswordField();
        passwordBox.setForeground(Color.BLACK);
        passwordBox.setBackground(Color.GRAY);
        passwordBox.setBounds(515, 600, 350, 30);
        add(passwordBox);

        setVisible(true);
    }
}
