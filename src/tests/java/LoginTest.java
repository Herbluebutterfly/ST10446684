package src.tests.java;

import org.junit.Test;

import src.Login;

import static org.junit.Assert.*;

public class LoginTest {

    //Part 1 unit testing:

    //Test #1: check if first name is not empty 

    @Test
    public void testCheckFirstNameNotEmpty() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isFirstNameValid = login.checkFirstName("John");
        assertTrue("First name should be valid if not be empty", isFirstNameValid);
    }

    //Test #2: check if first name is empty

    @Test
    public void testCheckFirstNameEmpty() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "", "Doe");
        boolean isFirstNameValid = login.checkFirstName("");
        assertFalse("First name should be invalid if not be empty", isFirstNameValid);
    }

    //Test #3: check if last name is not empty

    @Test
    public void testCheckLastNameNotEmpty() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isLastNameValid = login.checkLastName("Doe");
        assertTrue("Last name should be valid if not be empty", isLastNameValid);
    }

    //Test #4: check if last name is empty

    @Test
    public void testCheckLastNameEmpty() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "");
        boolean isLastNameValid = login.checkLastName("");
        assertFalse("Last name should be invalid if not be empty", isLastNameValid);
    }

    //Test #5: check if the username is correctly formatted

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("kyl_1", "password", "John", "Doe");
        boolean isUsernameValid = login.checkUserName("kyl_1");
        assertTrue("Username should be valid when correctly formatted", isUsernameValid);
    }

    //Test #6:  check if the username is not correctly formatted

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login("kyle!!!!!!!", "password", "John", "Doe");
        boolean isUsernameValid = login.checkUserName("kyle!!!!!!!");
        assertFalse("Username should be invalid when incorrectly formatted", isUsernameValid);
    }
    
    //Test #7:  check if the password meets complexity requirements.

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isPasswordValid = login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue("Password should fail complexity requirements", isPasswordValid);
    }

     // Test #8: check if successful registration has valid inputs.
     @Test
     public void testSuccessfulRegistration() {
         Login login = new Login("", "", "", "");
         String registrationStatus = login.registerUser("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
         assertEquals("Congratulations! You have successfully registered your account.", registrationStatus);
     }
 
     // Test #9: check if failed registration has invalid username.
     @Test
     public void testRegistrationInvalidUsername() {
         Login login = new Login("", "", "", "");
         String registrationStatus = login.registerUser("kyle", "Ch&&sec@ke99!", "John", "Doe");
         assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.", registrationStatus);
     }
 
     // Test #10: Check if failed registration has invalid password.
     @Test
     public void testRegistrationInvalidPassword() {
         Login login = new Login("", "", "", "");
         String registrationStatus = login.registerUser("kyl_1", "password", "John", "Doe");
         assertEquals("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.", registrationStatus);
     }
    
    //Test #11: to verify successful login details with the correct username and password.

    @Test
    public void testLoginSuccessful() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isLoginSuccessful = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue("Login should be successful with correct username and password", isLoginSuccessful);
    }

    //Test #12: to verify login failure with the correct username but incorrect password.

    @Test
    public void testLoginFailed() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isLoginSuccessful = login.loginUser("kyl_1", "wrongpassword");
        assertFalse("Login should fail with incorrect password", isLoginSuccessful);
    }

    //Test #13: to  verify login failure with the incorrect username but correct password.

    @Test
    public void testLoginFailedUsername(){
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        boolean isLoginSuccessful = login.loginUser("wronguser", "Ch&&sec@ke99!");
        assertFalse("Login should fail with incorrect username", isLoginSuccessful);
    }

    
}
