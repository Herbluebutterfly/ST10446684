
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UsernameTest {
    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("kyl_1", "password", "John", "Doe");
        boolean isUsernameValid = login.checkUserName("kyl_1");
        assertTrue(isUsernameValid);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login("kyle!!!!!!!", "password", "John", "Doe");
        boolean isUsernameValid = login.checkUserName("kyle!!!!!!!");
        assertFalse(isUsernameValid);
    }
}
    
