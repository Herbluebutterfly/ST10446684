
import org.junit.Test;

import src.Task;

import static org.junit.Assert.*;

public class TaskTest {
    //Test #1: Test the task name with the correct input

    @Test
    public void testValidateTaskName_Success() {
        assertTrue(Task.validateTaskName("Login Feature"));
    }

    //Test #2: Test the task name with the incorrect input
    @Test
    public void testValidateTaskName_Empty() {
        assertFalse(Task.validateTaskName(""));
    }


    // Test #4: Test the the developer's details is the correct input
    @Test
    public void testValidateDeveloperDetails_Success() {
        assertTrue(Task.validateDeveloperDetails("Mike Smith"));
    }

    //Test #5: Test the developer's details with the incorrect input
    @Test
    public void testValidateDeveloperDetails_Empty() {
        assertFalse(Task.validateDeveloperDetails(""));
    }


    //Test #7: Test the developer's details if there is no last name 
    @Test
    public void testValidateDeveloperDetails_NoLastName() {
        assertFalse(Task.validateDeveloperDetails("Mike"));
    }

    //Test #8: Test a valid choice (1-3) 
    @Test
    public void testValidateChoice_ValidChoice1() {
        assertEquals(1, Task.validateChoice("1"));
    }
    //Test #8: Test a valid choice (1-3) 
    @Test
    public void testValidateChoice_ValidChoice2() {
        assertEquals(2, Task.validateChoice("2"));
    }

    //Test #8: Test a valid choice (1-3) 
    @Test
    public void testValidateChoice_ValidChoice3() {
        assertEquals(3, Task.validateChoice("3"));
    }

    //Test #9: Invalid choice (out of bounds)
    @Test
    public void testValidateChoice_InvalidChoice_OutOfBounds() {
        assertEquals(-1, Task.validateChoice("4"));  
    }

    //Test #10: Invalid input (not a number)
    @Test
    public void testValidateChoice_NonNumeric() {
        assertEquals(-1, Task.validateChoice("abc"));  
    }

    //Test #11: Empty input
    @Test
    public void testValidateChoice_EmptyInput() {
        assertEquals(-1, Task.validateChoice(""));  
    }
    //Test #12: Test for task description length
    @Test
    public void testTaskDescriptionLength() {
        Task task = new Task("Login Feature", "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        assertTrue("Task description should be valid", task.checkTaskDescription());

        Task longTask = new Task("Long Task", "This description is way too long and exceeds fifty characters.", "Robyn Harrison", 8, "To Do");
        assertFalse("Task description should be invalid", longTask.checkTaskDescription());
    }

    //Test #13: Test for task ID generation
    @Test
    public void testCreateTaskID() {
        Task task = new Task("Add Task Feature", "Create Add Task feature", "Mike Smith", 10, "Doing");
        assertEquals("Task ID should be correctly generated", "AD:0:ITH", task.createTaskID());

        Task anotherTask = new Task("Feature", "Short description", "Alice Walker", 5, "To Do");
        assertEquals("Task ID should be correctly generated", "FE:1:KER", anotherTask.createTaskID());
    }

    //Test #14: Test for total hours accumulation
    @Test
    public void testTotalHoursAccumulation() {
        Task[] tasks = new Task[3];
        tasks[0] = new Task("Task 1", "First Task", "Dev One", 10, "To Do");
        tasks[1] = new Task("Task 2", "Second Task", "Dev Two", 12, "Doing");
        tasks[2] = new Task("Task 3", "Third Task", "Dev Three", 15, "Done");

        int totalHours = Task.returnTotalHours(tasks);
        assertEquals("Total hours should be 37", 37, totalHours);
    }
}
