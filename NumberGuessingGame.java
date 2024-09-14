import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    private static final int MAX_ATTEMPTS = 10;
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;
    private static int totalScore = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        int roundNumber = 1;

        while (playAgain) {
            playRound(roundNumber, scanner);
            playAgain = askToPlayAgain(scanner);
            roundNumber++;
        }

        System.out.println("Thank you for playing! Your final score is: " + totalScore);
        scanner.close();
    }

    private static void playRound(int roundNumber, Scanner scanner) {
        int numberToGuess = generateRandomNumber();
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("Round " + roundNumber + ": Guess the number between " + LOWER_BOUND + " and " + UPPER_BOUND + ". You have " + MAX_ATTEMPTS + " attempts.");

        while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
            int userGuess = getUserGuess(scanner);
            attempts++;
            guessedCorrectly = checkGuess(userGuess, numberToGuess, attempts);
        }

        if (!guessedCorrectly) {
            System.out.println("You've used all your attempts. The correct number was: " + numberToGuess);
        }

        updateScore(attempts);
    }

    private static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
    }

    private static int getUserGuess(Scanner scanner) {
        System.out.print("Enter your guess: ");
        return scanner.nextInt();
    }

    private static boolean checkGuess(int userGuess, int numberToGuess, int attempts) {
        if (userGuess == numberToGuess) {
            System.out.println("Congratulations! You guessed the number correctly in " + attempts + " attempts.");
            return true;
        } else if (userGuess < numberToGuess) {
            System.out.println("Your guess is too low.");
        } else {
            System.out.println("Your guess is too high.");
        }
        return false;
    }

    private static void updateScore(int attempts) {
        totalScore += MAX_ATTEMPTS - attempts;
        System.out.println("Your score for this round: " + (MAX_ATTEMPTS - attempts));
        System.out.println("Total score: " + totalScore);
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play another round? (yes/no): ");
        String response = scanner.next();
        return response.equalsIgnoreCase("yes");
    }
}
