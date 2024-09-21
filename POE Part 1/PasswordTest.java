import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordTest {
    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isPasswordValid = login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue("Password should fail complexity requirements", isPasswordValid);
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Login login = new Login("kyl_1", "password", "John", "Doe");
        boolean isPasswordValid = login.checkPasswordComplexity("password");
        assertFalse("Password should meet complexity requirements", isPasswordValid);
    }
}