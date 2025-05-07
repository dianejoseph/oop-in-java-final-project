import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import levels.*;


public class SudokuGUI {

    private static final int SIZE = 9;

    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private boolean[][] isUserEditable = new boolean[SIZE][SIZE];
    private boolean[][] hasBeenMarkedWrong = new boolean[SIZE][SIZE];
    private JFrame frame = new JFrame("Sudoku");
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private int lives = 5;
    private JLabel livesLabel = new JLabel("Lives: " + lives);
    
    private Level currentLevel;

    private int[][] currentSolution = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SudokuGUI().createAndShowGUI();
            }
        });
    }

    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        mainPanel.add(createTitleScreen(), "Title");
        mainPanel.add(getGamePanel(), "Game");

        frame.add(mainPanel);
        cardLayout.show(mainPanel, "Title");
        frame.setVisible(true);
    }

    private JPanel createTitleScreen() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel titleLabel = new JLabel("Super Sudoku!");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        easyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentLevel = new Level1(); // use the level class
                cardLayout.show(mainPanel, "Game");
                loadPuzzle();
            }
        });

        mediumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentLevel = new Level2(); // use the level class
                cardLayout.show(mainPanel, "Game");
                loadPuzzle();
            }
        });

        hardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentLevel = new Level3(); // use the level class
                cardLayout.show(mainPanel, "Game");
                loadPuzzle();
            }
        });
        

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        titlePanel.add(easyButton);
        titlePanel.add(mediumButton);
        titlePanel.add(hardButton);
        return titlePanel;
    }

    public JPanel getGamePanel() {
        JPanel gamePanel = new JPanel(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        Font font = new Font("SansSerif", Font.BOLD, 18);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField textField = new JTextField();
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(font);
                textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DigitFilter());
                cells[row][col] = textField;
                gridPanel.add(textField);
            }
        }

        JPanel buttonPanel = new JPanel();

        JButton checkButton = new JButton("Check");


        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        buttonPanel.add(checkButton);

        gamePanel.add(livesLabel, BorderLayout.NORTH);
        gamePanel.add(gridPanel, BorderLayout.CENTER);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);

        return gamePanel;
    }

    public void loadPuzzle() {
        clearBoard();
        int[][] puzzle = currentLevel.getPuzzle();
        currentSolution = currentLevel.getSolution();
        currentLevel.setLevel();
    
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = puzzle[row][col];
                if (value != 0) {
                    cells[row][col].setText(String.valueOf(value));
                    cells[row][col].setEditable(false);
                    isUserEditable[row][col] = false;
                } else {
                    cells[row][col].setText("");
                    cells[row][col].setEditable(true);
                    isUserEditable[row][col] = true;
                }
                hasBeenMarkedWrong[row][col] = false;
            }
        }
    }
    

    public void clearBoard() {
        lives = 3;
        livesLabel.setText("Lives: " + lives);
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col].setText("");
                cells[row][col].setEditable(true);
                cells[row][col].setBackground(Color.WHITE);
                isUserEditable[row][col] = true;
                hasBeenMarkedWrong[row][col] = false;
            }
        }
    }

    public void checkAnswer() {
        if (currentSolution == null) {
            return;
        }

        int newMistakes = 0;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (!isUserEditable[row][col]) {
                    continue;
                }

                String text = cells[row][col].getText().trim();

                if (text.isEmpty()) {
                    cells[row][col].setBackground(Color.WHITE);
                    continue;
                }

                try {
                    int value = Integer.parseInt(text);
                    if (value == currentSolution[row][col]) {
                        cells[row][col].setBackground(new Color(144, 238, 144));
                        hasBeenMarkedWrong[row][col] = false;
                    } else {
                        cells[row][col].setBackground(new Color(255, 160, 122));
                        if (!hasBeenMarkedWrong[row][col]) {
                            newMistakes++;
                            hasBeenMarkedWrong[row][col] = true;
                        }
                    }
                } catch (NumberFormatException ex) {
                    cells[row][col].setBackground(new Color(255, 160, 122));
                }
            }
        }

        if (newMistakes > 0) {
            lives -= newMistakes;
            livesLabel.setText("Lives: " + lives);
        }

        if (lives <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over! You're out of lives.");
            disableAllCells();
        }
    }

    public void disableAllCells() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col].setEditable(false);
            }
        }
    }

    public static class DigitFilter extends DocumentFilter {
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text.matches("[1-9]?")) {
                fb.replace(offset, length, text, attrs);
            }
        }
    }
}
