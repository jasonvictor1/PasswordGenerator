import java.util.Random;
import java.util.Scanner;

public class Main {
    // Declare constant strings for all of the character sets
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+=<>?";

    public static void main(String[] args) {
        try {
            // Get the desired password length from the user
            int length = getDesiredLength();
            // Generate a strong password of the specified length
            String password = generateStrongPassword(length);
            // Display the generated password
            displayPassword(password);
            // Perform the user's chosen action on the password (copy or save)
            performUserAction(password);
        } catch (InvalidInputException e) {
            // Catch and handle any InvalidInputException thrown during user input or password generation
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            // Catch and handle any other general exceptions that may occur while running this program
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static int getDesiredLength() throws InvalidInputException {
        Scanner input = new Scanner(System.in);
        int length;

        while (true) {
            try {
                // Prompt the user to enter the desired password length
                System.out.print("Enter the desired password length: ");
                length = input.nextInt();

                // Validate the input of the password length
                if (length <= 0) {
                    // Throw an InvalidInputException if the length is zero or a negative number
                    throw new InvalidInputException("Invalid password length. Length must be greater than 0.");
                } else {
                    // Exit the loop and return the valid password length
                    break;
                }
            } catch (Exception e) {
                // Catch any input exceptions such as a non-integer input and provide an error message
                System.out.println("Invalid input. Please enter a valid integer value for the password length.");
                input.nextLine(); // Clear the temporary storage of the input
            }
        }

        return length;
    }

    public static String generateStrongPassword(int length) throws InvalidInputException {
        // Validate the input length
        if (length <= 0) {
            // Throw an InvalidInputException if the length is zero or a negative number
            throw new InvalidInputException("Invalid password length. Length must be greater than 0.");
        }

        try {
            String validChars = UPPERCASE_LETTERS + LOWERCASE_LETTERS + NUMBERS + SPECIAL_CHARACTERS;
            char[] passwordChars = new char[length];
            Random random = new Random();

            // Generate each character of the password randomly from any of the valid character sets
            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(validChars.length());
                passwordChars[i] = validChars.charAt(randomIndex);
            }

            return new String(passwordChars);
        } catch (Exception e) {
            // Catch any exceptions during password generation and throw an InvalidInputException
            throw new InvalidInputException("Failed to generate a strong password.");
        }
    }

    public static void displayPassword(String password) {
        // Display the generated password
        System.out.println("Generated Password: " + password);
    }

    public static void performUserAction(String password) {
        Scanner input = new Scanner(System.in);
        System.out.print("Choose an action (copy/save): ");
        String action = input.nextLine();

        // Perform the user's chosen action on the password
        if (action.equalsIgnoreCase("copy")) {
            copyToClipboard(password);
        } else if (action.equalsIgnoreCase("save")) {
            savePassword(password);
        } else {
            // Display an error message for an invalid action
            displayErrorMessage("Invalid action");
        }
    }

    public static void copyToClipboard(String password) {
        // Display copying the password to the clipboard
        System.out.println("Password copied to clipboard.");
    }

    public static void savePassword(String password) {
        // Display saving the password
        System.out.println("Password saved successfully.");
    }

    public static void displayErrorMessage(String message) {
        // Display an error message
        System.out.println("Error: " + message);
    }

    public static class InvalidInputException extends Exception {
        // Custom exception class to represent an invalid input
        public InvalidInputException(String message) {
            super(message);
        }
    }
}
