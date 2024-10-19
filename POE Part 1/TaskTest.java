import org.junit.Test;
import static org.junit.Assert.*;

public class TaskTest {
    // Test for task description length
    @Test
    public void testTaskDescriptionLength() {
        Task task = new Task("Login Feature", "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        assertTrue("Task description should be valid", task.checkTaskDescription());

        Task longTask = new Task("Long Task", "This description is way too long and exceeds fifty characters.", "Robyn Harrison", 8, "To Do");
        assertFalse("Task description should be invalid", longTask.checkTaskDescription());
    }

    // Test for task ID generation
    @Test
    public void testCreateTaskID() {
        Task task = new Task("Add Task Feature", "Create Add Task feature", "Mike Smith", 10, "Doing");
        assertEquals("Task ID should be correctly generated", "AD:0:ITH", task.createTaskID());

        Task anotherTask = new Task("Feature", "Short description", "Alice Walker", 5, "To Do");
        assertEquals("Task ID should be correctly generated", "FE:1:KER", anotherTask.createTaskID());
    }

    // Test for total hours accumulation
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
