import java.util.regex.*;

public class Login {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    // Constructor using this referencing
    public Login(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Method to check if first name is not empty
    public boolean checkFirstName(String firstName) {
        if (firstName.length() == 0) {
            System.out.println("First name cannot be empty");
            return false;
        }
        return true;
    }

    // Method to check if last name is not empty
    public boolean checkLastName(String lastName) {
        if (lastName.length() == 0) {
            System.out.println("Last name cannot be empty");
            return false;
        }
        return true;
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

    // Method to validate password complexity
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

    // New registerUser() method
    public String registerUser(String username, String password, String firstName, String lastName) {
        if (!checkFirstName(firstName)) {
            return "First name cannot be empty.";
        }
        if (!checkLastName(lastName)) {
            return "Last name cannot be empty.";
        }
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
        }
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        return "User successfully registered.";
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
}