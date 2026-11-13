
import java.util.Scanner;

/**
 * Handles and validates user input for Emergency Ready.
 * Prevents invalid input from causing the application to crash.
 *
 * @author Madison Le
 * @version 2026-11-09
 */
public class InputHandler {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Reads and validates integer input.
     * Re-prompts the user until a valid integer is entered.
     *
     * @return the integer entered by the user
     */
    public static int getIntInput() {
        while (true) {
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Try again: ");
            }
        }
    }


    /**
     * Reads and validates string input.
     * Rejects empty or whitespace-only input.
     *
     * @return the non-empty string entered by the user
     */
    public static String getStringInput() {
        while (true) {
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty.");
            System.out.print("Try again: ");
        }
    }


    /**
     * Reads and validates boolean input.
     * Accepts yes/no or true/false.
     *
     * @return true if the user enters yes or true,
     *         false if the user enters no or false
     */
    public static boolean getBooleanInput() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("true")) {
                return true;
            }
            else if (input.equals("no") || input.equals("false")) {
                return false;
            }

            System.out.println("Invalid input. Please enter yes or no.");
            System.out.print("Try again: ");
        }
    }


    /**
     * Replaces the scanner for testing purposes.
     *
     * @param newScanner
     *            scanner used for testing
     */
    static void setScanner(Scanner newScanner) {
        scanner = newScanner;
    }
}
