import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test
    public void testLoginSuccessful() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isLoginSuccessful = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(isLoginSuccessful);
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isLoginSuccessful = login.loginUser("kyl_1", "wrongpassword");
        assertFalse(isLoginSuccessful);
    }
}
