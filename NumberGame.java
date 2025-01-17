import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        // Create Scanner and Random objects
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;
        // int number = random.nextInt(124) + 1; // Generate random number between 1 to
        // 124
        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int number = random.nextInt(124) + 1; // Generate random number between 1 to 124
            int attempts = 0;
            int maxAttempts = 10;

            boolean isCorrect = false;

            System.out.println("\n I have picked a number between 1 and 124.");
            System.out.println("You have " + maxAttempts + " tries to guess it!");
            // System.out.println("Hint: " + number);
            while (attempts < maxAttempts && !isCorrect) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;
                // System.out.println("Hint: " + number);
                if (guess == number) {
                    System.out.println("Yay! You guessed it in " + attempts + " tries.");
                    totalScore += maxAttempts - attempts + 1;
                    isCorrect = true;
                    // break;
                } else if (guess < number) {
                    System.out.println("Oops! The number is higher. Try again.");
                } else {
                    System.out.println("Oops! The number is lower. Try again.");
                }
                // System.out.println("Hint: " + number);
            }
            if (!isCorrect) {
                System.out.println("Sorry, you're out of tries. The number was: " + number);
            }
            // System.out.println("Hint: " + number);
            System.out.print("Do you want to play again? (yes/no): ");
            String answer = sc.next().toLowerCase();
            if (!answer.equals("yes")) {
                playAgain = false;
            }
        }
        System.out.println("Your total score is: " + totalScore);
        System.out.println("Thanks for playing!");
        sc.close();
    }
}