import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test
    public void testCheckUserNameCorrect() {
        Login login = new Login("John", "Doe", "john_", "Password@123");
        assertTrue(login.checkUserName(), "The username should be correctly formatted.");
    }

    @Test
    public void testCheckUserNameIncorrect() {
        Login login = new Login("John", "Doe", "johnDoe", "Password@123");
        assertFalse(login.checkUserName(), "The username should be incorrectly formatted.");
    }
}
