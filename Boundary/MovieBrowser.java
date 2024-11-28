package Boundary;

import Implementation.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovieBrowser extends JPanel{
    private JLabel header;
    private JLabel backButton;
    private Movie[] tentativeMovieList;

    MovieBrowser(JFrame mainWindow) {
        setLayout(null);
        // Label creation for the homepage
        header = new JLabel("Browse Movies");
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Calibri", Font.BOLD, 30));
        header.setBounds(515, 20, 400, 30);
        add(header);

        // Creating a back button
        backButton = new JLabel("Return Home");
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(new Font("Calibri", Font.PLAIN, 23));
        backButton.setBounds(20, 20, 200, 30);
        backButton.setForeground(Color.BLACK);
        add(backButton);


        // Add back button functionality
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
