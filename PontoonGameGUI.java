import javax.swing.*;
import java.awt.*;
import java.util.Random;

// Handles the GUI for the Pontoon game.
public class PontoonGameGUI extends JFrame {

    private GridSquare[][] gridSquares = new GridSquare[5][5];
    private JButton newGameButton;
    private JLabel statusLabel;
    private JLabel totalLabel;

    private int currentTotal = 0;
    private int currentPlayer = 1;
    private boolean gameOver = false;
    private Random random = new Random();

    public PontoonGameGUI() {
        setTitle("Pontoon Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Instruction label
        JLabel instructionLabel = new JLabel(
            "Keep the total below 22. Click 'New Game' to start.", 
            SwingConstants.CENTER
        );
        add(instructionLabel, BorderLayout.NORTH);

        // Grid panel
        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GridSquare square = new GridSquare();
                int row = i, col = j;
                square.addActionListener(e -> handleSquareClick(row, col));
                gridSquares[i][j] = square;
                gridPanel.add(square);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new GridLayout(3, 1));
        statusLabel = new JLabel("Press 'New Game' to start.", SwingConstants.CENTER);
        totalLabel = new JLabel("Current Total: 0", SwingConstants.CENTER);
        newGameButton = new JButton("New Game");
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(e -> startNewGame());

        bottomPanel.add(statusLabel);
        bottomPanel.add(totalLabel);
        bottomPanel.add(newGameButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(500, 600);
        setVisible(true);
    }

    //Starts a new game and assigns random values 1-5 to all squares.
    private void startNewGame() {
        currentTotal = 0;
        gameOver = false;
        currentPlayer = random.nextInt(2) + 1;
        totalLabel.setText("Current Total: 0");
        statusLabel.setText("Player " + currentPlayer + "'s turn.");

        for (GridSquare[] row : gridSquares) {
            for (GridSquare square : row) {
                square.setValue(random.nextInt(5) + 1); // values visible immediately
                square.reset();
            }
        }
    }

    //Handles the click on a grid square.
    private void handleSquareClick(int row, int col) {
        if (gameOver) return;

        GridSquare square = gridSquares[row][col];
        if (!square.isEnabled()) return;

        int playerWhoClicked = currentPlayer;
        square.claim(playerWhoClicked);

        currentTotal += square.getValue();
        totalLabel.setText("Current Total: " + currentTotal);

        if (currentTotal > 21) {
            gameOver = true;
            statusLabel.setText("Player " + playerWhoClicked + " wins! Total exceeded 21.");
            disableGrid();
        } else {
            switchPlayer();
            statusLabel.setText("Player " + currentPlayer + "'s turn.");
        }
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == 1 ? 2 : 1;
    }

    private void disableGrid() {
        for (GridSquare[] row : gridSquares) {
            for (GridSquare square : row) {
                square.setEnabled(false);
            }
        }
    }
}
