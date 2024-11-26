package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Registration extends JPanel {
    private JLabel test;

    public Registration(JFrame mainWindow) {
        test = new JLabel("Registration Page");
        test.setHorizontalAlignment(SwingConstants.CENTER);
        test.setForeground(Color.BLACK);
        test.setFont(new Font("Calibri", Font.BOLD, 30));
        test.setBounds(370, 50, 700, 30);
        add(test);
        setVisible(true);
    }
    
}
