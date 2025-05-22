package src;

import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        String word = "java";
        char[] guessed = new char[word.length()];
        for (int i = 0; i < guessed.length; i++) {
            guessed[i] = '_';
        }

        int attemptsLeft = 6;
        Scanner scanner = new Scanner(System.in);
        boolean wordGuessed = false;

        while (attemptsLeft > 0 && !wordGuessed) {
            System.out.println("Word: " + String.valueOf(guessed));
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            boolean correctGuess = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess && guessed[i] == '_') {
                    guessed[i] = guess;
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                attemptsLeft--;
                System.out.println("Wrong guess!");
            } else {
                System.out.println("Good guess!");
            }

            wordGuessed = true;
            for (char c : guessed) {
                if (c == '_') {
                    wordGuessed = false;
                    break;
                }
            }
            System.out.println();
        }

        if (wordGuessed) {
            System.out.println("Congratulations! You guessed the word: " + word);
        } else {
            System.out.println("Game over! The word was: " + word);
        }
        scanner.close();
    }
}