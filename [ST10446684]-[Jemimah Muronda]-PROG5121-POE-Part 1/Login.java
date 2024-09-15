import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Registration and Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 2));

        JLabel firstNameLabel = new JLabel("First Name");
        JTextField firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name");
        JTextField lastNameField = new JTextField();

        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();

        JLabel messageLabel = new JLabel("");

        JButton registerButton = new JButton("Register");

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                Login login = new Login(firstName, lastName, username, password);

                String registrationMessage = login.registerUser();
                messageLabel.setText(registrationMessage);

            }
        });
       
        frame.add(firstNameLabel);
        frame.add(firstNameField);
        frame.add(lastNameLabel);
        frame.add(lastNameField);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(registerButton);
        frame.add(messageLabel);
        frame.setVisible(true);
    }
}



