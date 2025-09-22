import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    public Screen(String title, int width, int height) {
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    public void showScreen() {
        setVisible(true);
    }

    public static void main(String[] args) {
        Screen screen = new Screen("Proposiciones - Equipo 7", 500, 400);
        screen.showScreen();
    }
}