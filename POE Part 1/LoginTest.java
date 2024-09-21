import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
    @Test
    public void testLoginSuccessful() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isLoginSuccessful = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue("Login should be successful with correct username and password", isLoginSuccessful);
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isLoginSuccessful = login.loginUser("kyl_1", "wrongpassword");
        assertFalse("Login should fail with incorrect password", isLoginSuccessful);
    }

    @Test
    public void testLoginFailedUsername(){
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isLoginSuccessful = login.loginUser("wronguser", "Ch&&sec@ke99!");
        assertFalse("Login should fail with incorrect username", isLoginSuccessful);
    }
}
