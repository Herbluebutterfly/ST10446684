import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login("", "", "", "");

        String firstName, lastName, username, password;

        System.out.println("--Register a new account--\n");

        // Validate first name after entry 
        boolean check = false;
        System.out.println("Enter your first name:");
        firstName = scanner.nextLine();
        while (!check) {
            if (login.checkFirstName(firstName)) {
                check = true;
            } else {
                System.out.println("First name cannot be empty. \nRe-enter your first name: ");
                firstName = scanner.nextLine();
            }
        }

        // Validate last name after entry 
        check = false;
        System.out.println("Enter your last name:");
        lastName = scanner.nextLine();
        while (!check) {
            if (login.checkLastName(lastName)) {
                check = true;
            } else {
                System.out.println("Last name cannot be empty. \nRe-enter your last name: ");

                lastName = scanner.nextLine();
            }
        }

        // Validate username after entry 
        check = false;
        System.out.println("\nRemember your username must: \n- not exceed 5 characters  \n- contain an underscore \nEnter you username: ");

        username = scanner.nextLine();
        while (!check) {
            if (login.checkUserName(username)) {
                check = true;
            } else {
                System.out.println("Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters in length. \nRe-enter your usename: ");
                username = scanner.nextLine();
            }
        }

        // Validate password after entry 
        check = false;
        System.out.println("\nRemember your password must:  \n- contain at least 8 characters  \n- contain a capital letter  \n- contain a special character \nEnter your password: ");
        password = scanner.nextLine();
        while (!check) {
            if (login.checkPasswordComplexity(password)) {
                check = true;
            } else {
                System.out.println("Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character. \nPlease enter your password again:");
                password = scanner.nextLine();
            }
        }

        // Register the user after validation
        System.out.println(login.registerUser(username, password, firstName, lastName));

        // The user will login after registration

        String loginUsername, loginPassword;
        boolean loginSuccess = false;
        
        System.out.println("\n--Login to your account--\n");
        
        System.out.println("Enter your username: ");
        loginUsername = scanner.nextLine();

        System.out.println("Enter your password: ");
        loginPassword = scanner.nextLine();

        // Validate login details
        while (!loginSuccess) {
            if (login.loginUser(loginUsername, loginPassword)) {
                loginSuccess = true;
            } else {
                System.out.println("Login failed. Username or password is incorrect.");
                System.out.println("Re-enter your username: ");
                loginUsername = scanner.nextLine();
                System.out.println("Re-enter your password: ");
                loginPassword = scanner.nextLine();
            }
        }
        System.out.println(login.returnLoginStatus(true));
        
        scanner.close();
    }
}
