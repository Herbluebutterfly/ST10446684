package java;

import org.junit.Test;

import src.Task;

import static org.junit.Assert.*;

import org.junit.Before;

public class TaskTest {

    // Unit Test for validateTaskName method
    @Test
    public void testValidateTaskName_Success() {
        assertTrue(Task.validateTaskName("Login Feature"));
    }

    @Test
    public void testValidateTaskName_Empty() {
        assertFalse(Task.validateTaskName(""));
    }

    // Unit Test for validateDeveloperDetails method
    @Test
    public void testValidateDeveloperDetails_Success() {
        assertTrue(Task.validateDeveloperDetails("Mike Smith"));
    }

    @Test
    public void testValidateDeveloperDetails_Empty() {
        assertFalse(Task.validateDeveloperDetails(""));
    }

    @Test
    public void testValidateDeveloperDetails_NoLastName() {
        assertFalse(Task.validateDeveloperDetails("Mike"));
    }

    // Unit Test for validateChoice method
    @Test
    public void testValidateChoice_ValidChoice1() {
        assertEquals(1, Task.validateChoice("1"));
    }

    @Test
    public void testValidateChoice_ValidChoice2() {
        assertEquals(2, Task.validateChoice("2"));
    }

    @Test
    public void testValidateChoice_ValidChoice3() {
        assertEquals(3, Task.validateChoice("3"));
    }

    @Test
    public void testValidateChoice_InvalidChoice_OutOfBounds() {
        assertEquals(-1, Task.validateChoice("9"));  // Invalid choice
    }

    @Test
    public void testValidateChoice_NonNumeric() {
        assertEquals(-1, Task.validateChoice("abc"));  // Invalid input
    }

    @Test
    public void testValidateChoice_EmptyInput() {
        assertEquals(-1, Task.validateChoice("")); // Empty input
    }

    // Test for Task Description validation (Less than 50 Characters)
    @Test
    public void testTaskDescription_Success() {
        Task task = new Task("Login Feature", "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        assertTrue(task.checkTaskDescription());
    }

    @Test
    public void testTaskDescription_Failure() {
        Task task = new Task("Add Task Feature", "This is a long description that exceeds the maximum limit of 50 characters.", "Mike Smith", 10, "Doing");
        assertFalse(task.checkTaskDescription());
    }

    // Test for Task ID Generation
    @Test
    public void testTaskID_Generation() {
        Task task1 = new Task("Login Feature", "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        Task task2 = new Task("Add Task Feature", "Create Add Task feature to add task users", "Mike Smith", 10, "Doing");

        assertEquals("LO:6:SON", task1.createTaskID());
        assertEquals("AD:6:ITH", task2.createTaskID());
    }

    // Test for Total Hours Calculation
    @Test
    public void testTotalHoursCalculation() {
        Task[] tasks = new Task[2];
        tasks[0] = new Task("Login Feature", "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        tasks[1] = new Task("Add Task Feature", "Create Add Task feature to add task users", "Mike Smith", 10, "Doing");

        assertEquals(18, Task.returnTotalHours(tasks));  // Should return total of 18 hours
    }

    // Additional test for multiple tasks (5 tasks with varying durations)
    @Test
    public void testTotalHoursAdditionalData() {
        Task[] tasks = new Task[5];
        tasks[0] = new Task("Task 1", "Description 1", "Dev One", 10, "To Do");
        tasks[1] = new Task("Task 2", "Description 2", "Dev Two", 12, "Doing");
        tasks[2] = new Task("Task 3", "Description 3", "Dev Three", 55, "Done");
        tasks[3] = new Task("Task 4", "Description 4", "Dev Four", 11, "To Do");
        tasks[4] = new Task("Task 5", "Description 5", "Dev Five", 1, "Doing");

        assertEquals(89, Task.returnTotalHours(tasks));  
    }

    //--Part 3 uint testing--
    
    private Task[] tasks;

    @Before
    public void setUp() {
        // Initialize the task array with test data
        tasks = new Task[4];
        tasks[0] = new Task("Create Login", "Login functionality", "Mike Smith", 5, "To Do");
        tasks[1] = new Task("Create Add Features", "Add new features", "Edward Harrison", 8, "Doing");
        tasks[2] = new Task("Create Reports", "Generate reports", "Samantha Paulson", 2, "Done");
        tasks[3] = new Task("Add Arrays", "Array functionality", "Glenda Oberholzer", 11, "To Do");
    }

    @Test
    public void testDeveloperArrayCorrectlyPopulated() {
        // Test that the developer array contains the expected data
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
        String[] actualDevelopers = new String[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            actualDevelopers[i] = tasks[i].getDeveloperDetails();
        }
        assertArrayEquals("Developers array should match expected test data", expectedDevelopers, actualDevelopers);
    }

    @Test
    public void testLongestDuration() {
        // Test that the task with the longest duration is correctly identified
        Task longestTask = tasks[0];
        for (Task task : tasks) {
            if (task.getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = task;
            }
        }
        assertEquals("Longest task developer should be Glenda Oberholzer", "Glenda Oberholzer", longestTask.getDeveloperDetails());
        assertEquals("Longest task duration should be 11", 11, longestTask.getTaskDuration());
    }

    @Test
    public void testSearchByTaskName() {
        // Test searching for a task by name
        String searchName = "Create Login";
        Task foundTask = null;
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(searchName)) {
                foundTask = task;
                break;
            }
        }
        assertNotNull("Task should be found", foundTask);
        assertEquals("Task developer should be Mike Smith", "Mike Smith", foundTask.getDeveloperDetails());
        assertEquals("Task name should be Create Login", "Create Login", foundTask.getTaskName());
    }

    @Test
    public void testSearchTasksByDeveloper() {
        // Test searching for tasks by developer name
        String searchDeveloper = "Samantha Paulson";
        StringBuilder taskNames = new StringBuilder();
        for (Task task : tasks) {
            if (task.getDeveloperDetails().equalsIgnoreCase(searchDeveloper)) {
                taskNames.append(task.getTaskName()).append("\n");
            }
        }
        assertTrue("Tasks assigned to Samantha Paulson should include Create Reports", taskNames.toString().contains("Create Reports"));
    }

    @Test
    public void testDeleteTask() {
        // Test deleting a task by name
        String taskNameToDelete = "Create Reports";
        boolean deleted = false;

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null && tasks[i].getTaskName().equalsIgnoreCase(taskNameToDelete)) {
                tasks[i] = null; // Delete the task
                deleted = true;
                break;
            }
        }

        assertTrue("Task should be deleted successfully", deleted);

        // Verify the task no longer exists
        boolean taskExists = false;
        for (Task task : tasks) {
            if (task != null && task.getTaskName().equalsIgnoreCase(taskNameToDelete)) {
                taskExists = true;
                break;
            }
        }
        assertFalse("Task should no longer exist", taskExists);
    }

    @Test
    public void testDisplayReport() {
        // Test generating a report of all tasks
        StringBuilder report = new StringBuilder();
        for (Task task : tasks) {
            if (task != null) {
                report.append(task.printTaskDetails()).append("\n");
            }
        }
        assertTrue("Report should include task details for Create Login", report.toString().contains("Create Login"));
        assertTrue("Report should include task details for Add Arrays", report.toString().contains("Add Arrays"));
    }
}

   