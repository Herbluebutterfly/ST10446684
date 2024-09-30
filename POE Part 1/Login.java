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

    // Method #1: to check if first name is not empty
    public boolean checkFirstName(String firstName) {
        return firstName.length() > 0;
    }

    // Method #2: to check if last name is not empty
    public boolean checkLastName(String lastName) {
        return lastName.length() > 0;
    }

    // Method #3: to validate username
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Method #4: to validate password complexity
    public boolean checkPasswordComplexity(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // Method #5: to confirm registration
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
        return "Congratulations! You have successfully registered your account.";
    }

    // Method #6: to validate login details
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    // Method #7: to return login status
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again!";
        } else {
            return "Username or password is incorrect, please try again.";
        }
    }
}
