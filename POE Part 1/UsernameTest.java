import org.junit.Test;
import static org.junit.Assert.*;

public class UsernameTest {
    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("kyl_1", "password", "John", "Doe");
        boolean isUsernameValid = login.checkUserName("kyl_1");
        assertTrue("Username should be valid when correctly formatted", isUsernameValid);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login("kyle!!!!!!!", "password", "John", "Doe");
        boolean isUsernameValid = login.checkUserName("kyle!!!!!!!");
        assertFalse("Username should be invalid when incorrectly formatted", isUsernameValid);
    }
}
    
