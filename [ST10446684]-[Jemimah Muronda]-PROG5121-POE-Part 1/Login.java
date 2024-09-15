import java.util.regex.*;

public class Login{
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public Login(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public boolean checkUserName(){
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(){
        String passwordPattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public String registerUser(){
        if (!checkUserName()){
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        } else if (checkPasswordComplexity()){
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
        } else{
            return "Username and password successfully captured.";
        }
    }

    public boolean loginUser(String enteredUsername, String enteredPassword){
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    public String returnLoginStatus(boolean loginSuccess){
        if(loginSuccess){
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again!";
        }else{
            return "Username or password is incorrect, please try again.";
        }
    }


    
}

