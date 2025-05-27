package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hangman extends JFrame {
    private String word = "java";
    private char[] guessed = {'_', '_', '_', '_'};
    private int attemptsLeft = 6;

    private JLabel wordLabel = new JLabel(String.valueOf(guessed));
    private JTextField guessField = new JTextField(1);
    private HangmanPanel hangmanPanel = new HangmanPanel();

    public Hangman() {
        setTitle("Hangman Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Guess the word: "));
        topPanel.add(wordLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        JButton guessButton = new JButton("Guess");
        inputPanel.add(new JLabel("Enter a letter:"));
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        add(inputPanel, BorderLayout.SOUTH);

        add(hangmanPanel, BorderLayout.CENTER);

        guessButton.addActionListener(e -> {
            if (guessField.getText().length() > 0) {
                char guess = Character.toLowerCase(guessField.getText().charAt(0));
                guessField.setText("");
                processGuess(guess);
            }
        });

        setVisible(true);
    }

    private void processGuess(char guess) {
        boolean correct = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess && guessed[i] == '_') {
                guessed[i] = guess;
                correct = true;
            }
        }

        if (!correct) {
            attemptsLeft--;
        }

        wordLabel.setText(String.valueOf(guessed));
        hangmanPanel.setAttemptsLeft(attemptsLeft);

        if (String.valueOf(guessed).equals(word)) {
            JOptionPane.showMessageDialog(this, "You won! The word was: " + word);
            System.exit(0);
        } else if (attemptsLeft == 0) {
            JOptionPane.showMessageDialog(this, "Game over! The word was: " + word);
            System.exit(0);
        }
    }
    class HangmanPanel extends JPanel {
        private int attemptsLeft = 6;

        public void setAttemptsLeft(int attemptsLeft) {
            this.attemptsLeft = attemptsLeft;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawLine(50, 250, 150, 250);
            g.drawLine(100, 250, 100, 50);
            g.drawLine(100, 50, 180, 50);
            g.drawLine(180, 50, 180, 70);

            if (attemptsLeft <= 5)
                g.drawOval(160, 70, 40, 40);
            if (attemptsLeft <= 4)
                g.drawLine(180, 110, 180, 170);
            if (attemptsLeft <= 3)
                g.drawLine(180, 120, 150, 150);
            if (attemptsLeft <= 2)
                g.drawLine(180, 120, 210, 150);
            if (attemptsLeft <= 1)
                g.drawLine(180, 170, 150, 200);
            if (attemptsLeft <= 0)
                g.drawLine(180, 170, 210, 200);
        }
    }

}
