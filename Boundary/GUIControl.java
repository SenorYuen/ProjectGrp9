package Boundary;

import javax.swing.JFrame;

public class GUIControl extends JFrame {
    private static Homepage homepage;
    private static final long serialVersionUID = 1L;

    GUIControl() {
        homepage = new Homepage();
        setTitle("title");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1366, 768);
        setContentPane(homepage);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }
}
