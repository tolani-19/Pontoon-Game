import javax.swing.*;
import java.awt.*;

/**
 * Represents a single square on the 5x5 Pontoon grid.
 * Encapsulates the square's value, ownership, and display behavior.
 */
public class GridSquare extends JButton {

    private int value;
    private int owner; 
    private final Color PLAYER1_COLOR = Color.BLUE;
    private final Color PLAYER2_COLOR = Color.YELLOW;

    public GridSquare() {
        super("");
        setFont(new Font("Arial", Font.BOLD, 18));
        setEnabled(false); // disabled until New Game
        setOpaque(true);
        setContentAreaFilled(true);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    //Set the value of the square.
    public void setValue(int value) {
        this.value = value;
        setText(String.valueOf(value)); // show value immediately
    }

    //Get the square's value.
    public int getValue() {
        return value;
    }

    // Claim the square for a player.
    public void claim(int player) {
        owner = player;
        setBackground(owner == 1 ? PLAYER1_COLOR : PLAYER2_COLOR);
        setEnabled(false);
    }

    // Reset the square to initial unclaimed state.
    public void reset() {
        owner = 0;
        setEnabled(true); // keep value visible
        setBackground(null);
    }
}
