import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LoginStatusTest {
    @Test
    public void testLoginSucessful(){
        Login login = new Login("kyl_1", "Password1!", "John", "Doe");
        assertTrue(login.loginUser("kyl_1", "Pawssword1!"));
    }

    public void testLoginFailed(){
        Login login = new Login("kyl_1", "Password1!", "John", "Doe");
        assertFalse(login.loginUser("kyl_1", "Pawssword1!"));
    }
}
