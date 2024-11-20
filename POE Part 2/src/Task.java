package src;
import javax.swing.*;
public class Task {
    private static int taskCounter = 0;
    private String taskName;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskStatus;
    private String taskID;

    //Constructor for  Task class
    public Task(String taskName, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
        taskCounter++;
    }

        // Getter for taskName
        public String getTaskName() {
            return taskName;
        }
    
        // Setter for taskName
        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }
    
        // Getter for developerName
        public String getDeveloperDetails() {
            return developerDetails;
        }
    
        // Setter for developerName
        public void setDeveloperName(String developerDetails) {
            this.developerDetails = developerDetails;
        }
    
        // Getter for duration
        public int getTaskDuration() {
            return taskDuration;
        }
    
        // Setter for duration
        public void setDuration(int taskDuration) {
            this.taskDuration = taskDuration;
        }
    
        // Getter for status
        public String getTaskStatus() {
            return taskStatus;
        }
    
        // Setter for status
        public void setTaskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
        }

    // Method #1: validate the choice numbered 1-3
    public static int validateChoice(String input) {
        int choice = -1;

        try {
            choice = Integer.parseInt(input);  // Convert input to an integer
            if (choice < 1 || choice > 8) {
                JOptionPane.showMessageDialog(null, "Invalid option. Please enter a number between 1 and 8.");
                choice = -1;  // Reset choice if out of valid range
            }
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number (1-8).");
        }

        return choice;  // Return the validated choice or -1 if invalid
    }

    // Method #2: check if Task name is not empty
    public static boolean validateTaskName(String taskName) {
        if (taskName == null || taskName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Task name cannot be empty.");
            return false;
        }
        return true;
    }

    // Method #3: To check if user has entered both name and surname
    public static boolean validateDeveloperDetails(String developerDetails) {
        if (developerDetails == null || developerDetails.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Developer details cannot be empty.");
            return false;
        }
        String[] nameParts = developerDetails.trim().split("\\s+");
        if (nameParts.length < 2) {
            JOptionPane.showMessageDialog(null, "Developer details must include both first and last name.");
            return false;
        }
        return true;
    }

    //Method #4: make sure that the discription is no  longer than 50 characters.
    public boolean checkTaskDescription() {
        return taskDescription != null && taskDescription.length() <= 50;
    }

    //Method #5: to generate task ID
    public String createTaskID() {
        String initials = taskName.substring(0, 2).toUpperCase();
        String lastThreeLetters = developerDetails.length() >= 3 ? developerDetails.substring(developerDetails.length() - 3).toUpperCase() : "XXX";
        return initials + ":" + taskCounter + ":" + lastThreeLetters;
    }

    //Method #6: to display task details
    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
               "Developer Details: " + developerDetails + "\n" +
               "Task Number: " + (taskCounter - 1) + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Description: " + taskDescription + "\n" +
               "Task ID: " + taskID + "\n" +
               "Task Duration: " + taskDuration + " hours";
    }

    //Method #7: to calculate total task hours
    public static int returnTotalHours(Task[] tasks) {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.taskDuration;
        }
        return totalHours;
    }
}