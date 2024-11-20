
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
        assertEquals(-1, Task.validateChoice(""));  // Empty input
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

        assertEquals("LO:1:SON", task1.createTaskID());
        assertEquals("AD:1:ITH", task2.createTaskID());
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
        // Initialize the test data
        tasks = new Task[4];
        tasks[0] = new Task("Create Login", "Task for user login", "Mike Smith", 5, "To Do");
        tasks[1] = new Task("Create Add Features", "Add new features", "Edward Harrison", 8, "Doing");
        tasks[2] = new Task("Create Reports", "Generate reports", "Samantha Paulson", 2, "Done");
        tasks[3] = new Task("Add Arrays", "Add array functionality", "Glenda Oberholzer", 11, "To Do");
    }

    @Test
    public void testDeveloperArrayPopulation() {
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
        String[] actualDevelopers = new String[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            actualDevelopers[i] = tasks[i].getDeveloperDetails();
        }
        assertArrayEquals(expectedDevelopers, actualDevelopers);
    }

    @Test
    public void testDisplayLongestTaskDuration() {
        Task longestTask = tasks[0];
        for (Task task : tasks) {
            if (task.getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = task;
            }
        }
        assertEquals("Glenda Oberholzer", longestTask.getDeveloperDetails());
        assertEquals(11, longestTask.getTaskDuration());
    }

    @Test
    public void testSearchTaskByName() {
        String searchTaskName = "Create Login";
        Task foundTask = null;
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(searchTaskName)) {
                foundTask = task;
                break;
            }
        }
        assertNotNull(foundTask);
        assertEquals("Mike Smith", foundTask.getDeveloperDetails());
        assertEquals("Create Login", foundTask.getTaskName());
    }

    @Test
    public void testSearchTasksByDeveloper() {
        String searchDeveloper = "Samantha Paulson";
        String foundTaskName = null;
        for (Task task : tasks) {
            if (task.getDeveloperDetails().equalsIgnoreCase(searchDeveloper)) {
                foundTaskName = task.getTaskName();
                break;
            }
        }
        assertEquals("Create Reports", foundTaskName);
    }

    @Test
    public void testDeleteTaskByName() {
        String taskNameToDelete = "Create Reports";
        boolean deleted = false;
        
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null && tasks[i].getTaskName().equalsIgnoreCase(taskNameToDelete)) {
                tasks[i] = null;  // Simulate deletion
                deleted = true;
                break;
            }
        }

        assertTrue(deleted);

        // Confirm that task with the name "Create Reports" is no longer in the array
        for (Task task : tasks) {
            if (task != null && task.getTaskName().equalsIgnoreCase(taskNameToDelete)) {
                fail("Task 'Create Reports' was not successfully deleted.");
            }
        }
    }

    @Test
    public void testDisplayReport() {
        StringBuilder report = new StringBuilder();
        for (Task task : tasks) {
            if (task != null) {
                report.append(task.printTaskDetails()).append("\n\n");
            }
        }
        
        // Expected output for all tasks
        assertTrue(report.toString().contains("Mike Smith"));
        assertTrue(report.toString().contains("Edward Harrison"));
        assertTrue(report.toString().contains("Samantha Paulson"));
        assertTrue(report.toString().contains("Glenda Oberholzer"));
    }
}

   