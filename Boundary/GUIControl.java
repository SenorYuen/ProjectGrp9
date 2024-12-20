package Boundary;

/*
* File Name: GUIControl.java
* Assignment: Final Project
* Lab section: B02
* Completed by: Adam Yuen
* Development Date: November 28, 2024
*/

import javax.swing.JFrame;

import Implementation.TheaterPopulator;
import Implementation.Theater;

public class GUIControl extends JFrame {
    private static Homepage homepage;
    private static JFrame window;
    private static final long serialVersionUID = 1L;

    GUIControl() {
        // Create the JFrame in which all panels will be placed on.
        Theater theater = TheaterPopulator.populateTheater();
        window = new JFrame();
        homepage = new Homepage(window, theater);
        window.setTitle("title");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1366, 768);
        window.setContentPane(homepage);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setVisible(true);
    }
}
