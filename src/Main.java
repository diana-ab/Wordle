import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String winWord = randomWord(wordDatabase());

        System.out.println("Welcome to Wordle! In this game you have 6 tries to guess the correct word. Each word will not contain more than 5 letters.");
        if (!checksWinOrLose(winWord)) {
            System.out.println("You lost :( the winning word is !!" + winWord.toUpperCase() + "!!");
        } else {
            System.out.println("You won :) !!" + winWord.toUpperCase() + "!!");
        }
    }

    public static boolean checksWinOrLose(String winWord) {
        Scanner sca = new Scanner(System.in);
        int trials = 0;
        while (trials < 6) {
            System.out.println("Please input your guess: ");
            String guess = sca.nextLine();
            String guessLowerCase = guess.toLowerCase();
            if (!isValid(guessLowerCase, wordDatabase())) {
                System.out.println("Invalid input. Please try again.");
            } else {
                trials++;
                if (winWord.equals(guessLowerCase)) {
                    return true;
                } else {
                    char[] feedback = comparingCharInStrings(winWord, guessLowerCase);
                    System.out.println("This is trial number: " + trials + ". Your feedback is: ");
                    print(feedback);
                    System.out.println();

                }
            }
        }
        return false;
    }

    public static String[] wordDatabase() {
        String[] wordBank = {"music", "heart", "solar", "fruit", "comet", "flame", "shark", "earth", "space", "glass", "apple", "angle", "ninja", "stars", "lemon", "honey", "eagle", "mango", "night", "grape"};
        return wordBank;
    }

    public static String randomWord(String[] words) {
        Random random = new Random();
        int winNum = random.nextInt(0, 20);
        return words[winNum];
    }

    public static boolean isValid(String guess, String[] words) {
        if (guess.length() == 5) {
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(guess)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static char[] comparingCharInStrings(String winWord, String guess) {
        char[] feedback = new char[winWord.length()];
        for (int i = 0; i < winWord.length(); i++) {
            if (guess.charAt(i) == winWord.charAt(i)) {
                feedback[i] = 'G';
                continue;
            }
            for (int j = 0; j < winWord.length(); j++) {
                if (j != i && guess.charAt(i) == winWord.charAt(j)) {
                    feedback[i] = 'Y';
                }
            }
            if (feedback[i] != 'Y' && feedback[i] != 'G') {
                feedback[i] = '_';
            }
        }
        return feedback;
    }

    public static void print(char[] str) {
        for (int i = 0; i < str.length; i++) {
            System.out.print(str[i]);
        }
    }
}