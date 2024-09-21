import java.util.Scanner;
import java.util.regex.*;


public class Login {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    // Constructor using this refrencing 
    public Login(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Method to validate username
    public boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            System.out.println("Username successfully captured.");
            return true;
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            return false;
        }
    }

    // Method to validate password to make sure it meets requirements
    public boolean checkPasswordComplexity(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            System.out.println("Password successfully captured.");
            return true;
        } else {
            System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
            return false;
        }
    }

    // Method to validate login details
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    // Method to return login status
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again!";
        } else {
            return "Username or password is incorrect, please try again.";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Retrieve the user's first name and last name
        System.out.println("Register a new account");
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        // 
        String username;
        do {
            System.out.print("Enter your username: ");
            username = scanner.nextLine();
        } while (!new Login(username, "", firstName, lastName).checkUserName(username));

        // Validate password immediately after entry
        String password;
        do {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
        } while (!new Login(username, password, firstName, lastName).checkPasswordComplexity(password));

        // Create user after validation
        Login user = new Login(username, password, firstName, lastName);
        System.out.println("User successfully registered!");

        // Login user
        System.out.println("\nLogin to your account:");
        System.out.print("Enter your username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter your password: ");
        String loginPassword = scanner.nextLine();

        boolean isLoggedIn = user.loginUser(loginUsername, loginPassword);
        System.out.println(user.returnLoginStatus(isLoggedIn));

        scanner.close();
    }
}