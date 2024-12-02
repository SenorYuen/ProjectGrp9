package Boundary;

import util.DBConnection;
import util.Login;
import util.LoginSession;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ProgramRunner {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("ACMEPLEX Theater Portal");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(1366, 768);
            window.setResizable(false);
            window.setLocationRelativeTo(null);
            window.setLayout(null);

            Homepage homepage = new Homepage(window);
            window.setContentPane(homepage);
            window.setVisible(true);
        });
    }
}