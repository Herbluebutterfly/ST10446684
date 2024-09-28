import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Register a new account");

        // Validate first name immediately after entry
        String firstName;
        do {
            System.out.print("Enter your first name: ");
            firstName = scanner.nextLine();
        } while (!new Login("", "", firstName, "").checkFirstName(firstName));

        // Validate last name immediately after entry
        String lastName;
        do {
            System.out.print("Enter your last name: ");
            lastName = scanner.nextLine();
        } while (!new Login("", "", firstName, lastName).checkLastName(lastName));

        // Validate username immediately after entry
        String username;
        do {
            System.out.print("\nRemember your username must: \n- not exceed 5 characters  \n- contain an underscore \nEnter your username: ");
            username = scanner.nextLine();
        } while (!new Login(username, "", firstName, lastName).checkUserName(username));

        // Validate password immediately after entry
        String password;
        do {
            System.out.print("\nRemember your password must:  \n- contain at least 8 characters  \n- contain a capital letter  \n- contain a number  \n- contain a special character  \nEnter your password: ");
            password = scanner.nextLine();
        } while (!new Login(username, "", firstName, lastName).checkPasswordComplexity(password));

     // Register user after validation
     Login user = new Login(username, password, firstName, lastName);
     String registrationMessage = user.registerUser(username, password, firstName, lastName);
     System.out.println(registrationMessage);

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