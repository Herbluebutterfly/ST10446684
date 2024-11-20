package src;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    // Define class-level variables for tasks and numTasks
    private static Task[] tasks;
    private static int numTasks;

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

        // After a successful login, the welcome menu appears
        boolean running = true;

        while (running) {
            // Show menu options using JOptionPane and ask for user input
            String input = JOptionPane.showInputDialog(null, "Welcome to EasyKanban\n\n" +
                "1. Add tasks\n" +
                "2. Show completed tasks\n" +
                "3. Show task with the longest duration\n" + 
                "4. Search task by name\n" + 
                "5. Search for tasks by developer\n" + 
                "6. Delete task by name\n" + 
                "7. Display full report\n\n" + 
                "8. Quit");

            // it uses validation method located in the task class
            int choice = Task.validateChoice(input);

            switch (choice) {
                case 1:
                    addTasks();  // Call method to add tasks
                    break;
                case 2:
                    showCompletedTasks();
                    break;
                case 3:
                    showTaskLongestDuration();
                    break;
                case 4:
                    searchTaskName();
                    break;
                case 5:
                    searchDeveloper();
                    break;
                case 6:
                    deleteTaskName();
                    break;
                case 7:
                    displayReport();
                    break;
                case 8:
                    running = false; // Exit the loop to quit
                    JOptionPane.showMessageDialog(null, "Thank you for using EasyKanban!");
                    break;
                default:
                    // No need for this case as invalid input is handled in validateChoice
                    break;
            }
        }
    

        scanner.close();
    }

    public static void addTasks() {
        // Prompt user to enter the number of tasks they want to create
        numTasks = Integer.parseInt(JOptionPane.showInputDialog(null,
            "Enter the amount of tasks you want to create:",
            "Number of Tasks", JOptionPane.PLAIN_MESSAGE));
    
        // Dynamically set the size of the tasks array based on user input
        tasks = new Task[numTasks];
    
        for (int i = 0; i < numTasks; i++) {
            // Task Name Input and Validation
            String taskName;
            boolean validTaskName = false;
            taskName = JOptionPane.showInputDialog("Enter Task Name for Task " + (i + 1) + ":");
            while (!validTaskName) {
                if (Task.validateTaskName(taskName)) {
                    validTaskName = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Task Name. Try again.");
                    taskName = JOptionPane.showInputDialog("Enter Task Name for Task " + (i + 1) + ":");
                }
            }
    
            // Task Description Input and Validation
            String taskDescription;
            boolean validDescription = false;
            taskDescription = JOptionPane.showInputDialog("Enter Task Description (max 50 characters):");
            while (!validDescription) {
                Task tempTask = new Task(taskName, taskDescription, "", 0, "");
                if (tempTask.checkTaskDescription()) {
                    validDescription = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                    taskDescription = JOptionPane.showInputDialog("Enter Task Description (max 50 characters):");
                }
            }
    
            // Developer Details Input and Validation
            String developerDetails;
            boolean validDeveloperDetails = false;
            developerDetails = JOptionPane.showInputDialog("Enter Developer's First and Last Name:");
            while (!validDeveloperDetails) {
                if (Task.validateDeveloperDetails(developerDetails)) {
                    validDeveloperDetails = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Developer Name. Try again.");
                    developerDetails = JOptionPane.showInputDialog("Enter Developer's First and Last Name:");
                }
            }
    
            // Task Duration Input
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (in hours):"));
    
            // Task Status Selection
            String[] statusOptions = {"To Do", "Done", "Doing"};
            int statusChoice = JOptionPane.showOptionDialog(null, "Choose Task Status:", "Task Status",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, statusOptions, statusOptions[0]);
            String taskStatus = statusOptions[statusChoice];
    
            // Create Task and store in array
            Task task = new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
            tasks[i] = task;
    
            // Show Task Details
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }
    
        // Calculate and display total hours
        int totalHours = Task.returnTotalHours(tasks);
        JOptionPane.showMessageDialog(null, "Total hours for all tasks: " + totalHours);
    }    

    
    public static void showCompletedTasks() {
        StringBuilder completedTasks = new StringBuilder();
        int found = 0;
    
        for (int i = 0; i < numTasks; i++) {
            if (tasks[i] != null && tasks[i].getTaskStatus().equals("Done")) {
                completedTasks.append("Task Name: ").append(tasks[i].getTaskName())
                    .append("\nDeveloper: ").append(tasks[i].getDeveloperDetails())
                    .append("\nDuration: ").append(tasks[i].getTaskDuration())
                    .append(" hours\n\n");
                found++;
            }
        }
    
        if (found > 0) {
            JOptionPane.showMessageDialog(null, completedTasks.toString(), "Completed Tasks", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No completed tasks found.", "No Tasks", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void showTaskLongestDuration() {
        Task longestTask = tasks[0];
        for (int i = 1; i < numTasks; i++) {
            if (tasks[i] != null && tasks[i].getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = tasks[i];
            }
        }
        JOptionPane.showMessageDialog(null, "Task with the longest duration:\n\n" + longestTask.printTaskDetails(), "Longest Task", JOptionPane.PLAIN_MESSAGE);
    }

    public static void searchTaskName() {
        String searchName = JOptionPane.showInputDialog("Enter the task name to search for:");
        boolean found = false;
    
        for (int i = 0; i < numTasks; i++) {
            if (tasks[i] != null && tasks[i].getTaskName().equalsIgnoreCase(searchName)) {
                JOptionPane.showMessageDialog(null, "Task found:\n\n" + tasks[i].printTaskDetails(), "Task Found", JOptionPane.PLAIN_MESSAGE);
                found = true;
                break;
            }
        }
    
        if (!found) {
            JOptionPane.showMessageDialog(null, "Task not found.", "No Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void searchDeveloper() {
        String searchDeveloper = JOptionPane.showInputDialog("Enter the developer's name to search for:");
        boolean found = false;
    
        for (int i = 0; i < numTasks; i++) {
            if (tasks[i] != null && tasks[i].getDeveloperDetails().equalsIgnoreCase(searchDeveloper)) {
                JOptionPane.showMessageDialog(null, "Task by developer found:\n\n" + tasks[i].printTaskDetails(), "Developer Task", JOptionPane.PLAIN_MESSAGE);
                found = true;
                break;
            }
        }
    
        if (!found) {
            JOptionPane.showMessageDialog(null, "Developer not found.", "No Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void deleteTaskName() {
        String taskNameToDelete = JOptionPane.showInputDialog("Enter the task name to delete:");
        boolean deleted = false;
    
        for (int i = 0; i < numTasks; i++) {
            if (tasks[i] != null && tasks[i].getTaskName().equalsIgnoreCase(taskNameToDelete)) {
                tasks[i] = null;  // Delete the task
                deleted = true;
                break;
            }
        }
    
        if (deleted) {
            JOptionPane.showMessageDialog(null, "Task deleted successfully.", "Task Deleted", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.", "Delete Failed", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void displayReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < numTasks; i++) {
            if (tasks[i] != null) {
                report.append(tasks[i].printTaskDetails()).append("\n\n");
            }
        }
    
        if (report.length() > 0) {
            JOptionPane.showMessageDialog(null, report.toString(), "All Tasks Report", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks to display.", "No Tasks", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
