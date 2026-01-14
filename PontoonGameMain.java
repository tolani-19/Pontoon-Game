import javax.swing.*;

// Main class to launch the Pontoon game.
public class PontoonGameMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PontoonGameGUI());
    }
}
