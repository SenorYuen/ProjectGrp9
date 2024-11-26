package Boundary;

import javax.swing.JFrame;

public class GUIControl extends JFrame {
    private static Homepage homepage;
    private static JFrame window;
    private static final long serialVersionUID = 1L;

    GUIControl() {
        window = new JFrame();
        homepage = new Homepage(window);
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
