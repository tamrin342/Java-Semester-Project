package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController {
    @FXML
    private TextField loginEmailField;
    @FXML
    private TextField loginUserNameField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Label LoginErrorLabel;
    static String pfirstname,plastname,pemail,pusername,ppassword;
    @FXML
    public void LoginAction(ActionEvent event)
    {int count = 0;
        String loginemail = loginEmailField.getText();
        String loginusername = loginUserNameField.getText();
        String loginpassword = loginPasswordField.getText();

        if(loginemail.equals("") || loginusername.equals("") || loginpassword.equals("")){
            SignupController.ErrorAlert("Email/User Name/Password Is Empty!" , "Error");
        }
        else {
            try {
                String Query = "SELECT * FROM signupform;";
                Connection connection = SingletonConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(Query);
                while (resultSet.next()) {
                    count=1;
                    String email = resultSet.getString("Email");
                    String username = resultSet.getString("User_Name");
                    String password = resultSet.getString("Password");
                    String firstname = resultSet.getString("First_Name");
                    String lastname = resultSet.getString("Last_Name");

                    if (email.equals(loginemail) && username.equals(loginusername) && password.equals(loginpassword)) {
                        //initializing users information to display in the information section and to use the username later everywhere
                        pfirstname = firstname;
                        plastname = lastname;
                        pemail = email;
                        ppassword = password;
                        pusername = username;
                        HomeController.sceneChange("profile-view.fxml", event);
                        break;
                    }
                    else if (email.equals(loginemail) && username.equals(loginusername) && !password.equals(loginpassword))
                    {
                        LoginErrorLabel.setText("Incorrect Password!");
                    }
                    else if (!email.equals(loginemail) && username.equals(loginusername) && password.equals(loginpassword))
                    {
                        LoginErrorLabel.setText("Incorrect Email!");
                    }

                    else {
                        LoginErrorLabel.setText("User Not Found!");
                    }

                }
                if(count==0){
                    LoginErrorLabel.setText("User Not Found!");
                }
            } catch (SQLException ex) {
                System.err.println("Login SQLException");
            } catch (IOException e) {
                System.err.println("IO Exception");
            }
        }

    }
@FXML
public void RemovingErrorLabel(){
    LoginErrorLabel.setText("");
}
    @FXML
    public void LogintoSignupAction(ActionEvent event) throws IOException
    {
        HomeController.sceneChange("signup-view.fxml",event);
    }
    @FXML
    public void LogintoHomeAction(ActionEvent event) throws IOException
    {
        HomeController.sceneChange("home-view.fxml",event);
    }
}
