import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JDialog window = new JDialog();
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
        System.out.println("\nRemember your username must: \n- not exceed 5 characters  \n- contain an underscore \nEnter your username: ");
        username = scanner.nextLine();
        while (!check) {
            if (login.checkUserName(username)) {
                check = true;
            } else {
                System.out.println("Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters in length. \nRe-enter your username: ");
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

        // Login process
        while (!loginSuccess) {
            System.out.println("Enter your username: ");
            loginUsername = scanner.nextLine();

            System.out.println("Enter your password: ");
            loginPassword = scanner.nextLine();

            if (login.loginUser(loginUsername, loginPassword)) {
                loginSuccess = true;
            } else {
                System.out.println("Login failed. Username or password is incorrect.");
            }
        }

        System.out.println(login.returnLoginStatus(true));

        window.setAlwaysOnTop(loginSuccess);

        // Main menu after successful login
        boolean running = true;
        while (running) {
            String[] options = {"Add tasks", "Show report", "Quit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome to EasyKanban", "EasyKanban Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    addTasks();  // Call method to add tasks
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Coming Soon"); // Placeholder for report feature
                    break;
                case 2:
                    running = false; // Exit the loop to quit
                    JOptionPane.showMessageDialog(null, "Thank you for using EasyKanban!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    Task task = new Task(null, null, null, 0, null);

    // Method to add tasks
    public static void addTasks() {
        String numTasksStr = JOptionPane.showInputDialog("How many tasks do you want to add?");
        int numTasks = Integer.parseInt(numTasksStr);

        Task[] tasks = new Task[numTasks];

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");

            String taskDescription;
            boolean validDescription;
            do {
                taskDescription = JOptionPane.showInputDialog("Enter Task Description (max 50 characters):");
                Task tempTask = new Task(taskName, taskDescription, "", 0, "");
                validDescription = tempTask.checkTaskDescription();
                if (validDescription) {
                    JOptionPane.showMessageDialog(null, "Task successfully captured");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                }
            } while (!validDescription);

            String developerDetails = JOptionPane.showInputDialog("Enter Developer Details:");

            String taskDurationStr = JOptionPane.showInputDialog("Enter Task Duration (hours):");
            int taskDuration = Integer.parseInt(taskDurationStr);

            String[] statusOptions = {"To Do", "Done", "Doing"};
            int statusChoice = JOptionPane.showOptionDialog(null, "Choose Task Status:", "Task Status",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, statusOptions, statusOptions[0]);
            String taskStatus = statusOptions[statusChoice];

            Task task = new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
            tasks[i] = task;

            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }

        int totalHours = Task.returnTotalHours(tasks);
        JOptionPane.showMessageDialog(null, "Total hours for all tasks: " + totalHours);
    }
}