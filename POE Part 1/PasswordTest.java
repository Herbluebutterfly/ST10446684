import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PasswordTest {
    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isPasswordValid = login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue(isPasswordValid);
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Login login = new Login("kyl_1", "password", "John", "Doe");
        boolean isPasswordValid = login.checkPasswordComplexity("password");
        assertFalse(isPasswordValid);
    }
}