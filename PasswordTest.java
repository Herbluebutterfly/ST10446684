import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PasswordTest {
    @Test
    public void testCorrectPassword() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        assertTrue(login.checkPasswordComplexity(), "Username and password successfully captured.");
    }

    @Test
    public void testIncorrectPassword() {
        Login login = new Login("kyl_1", "password", "John", "Doe");
        assertFalse(login.checkPasswordComplexity(), "assword is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
    }
}
