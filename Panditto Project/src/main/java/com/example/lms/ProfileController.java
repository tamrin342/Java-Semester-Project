package com.example.lms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Label WelcomeFirstNameLabel,FirstNameLabel,LastNameLabel,FullNameLabel,UsernameLabel,EmailLabel;
    @FXML
    private PasswordField CurrentPasswordField,NewPasswordField,ConfirmPasswordField;
    @FXML
    public void UpdatePasswordAction()
    {
        String current_password = CurrentPasswordField.getText();
        String new_password = NewPasswordField.getText();
        String confirm_password = ConfirmPasswordField.getText();
        if(!new_password.equals(confirm_password))
        {
            SignupController.ErrorAlert("Confirm Password Doesn't Match with New Password" ,"Error");
        }
        if (!current_password.equals(LoginController.ppassword))
        {
            SignupController.ErrorAlert("Current Password Doesn't Match" ,"Error");
        }

        if(new_password.equals(confirm_password) && current_password.equals(LoginController.ppassword))
        {
            //update password from signupform database
            try{
                String supdatePasswordQuery = "UPDATE signupform SET Password =? WHERE User_Name = ?;";
                Connection connection = SingletonConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(supdatePasswordQuery);
                preparedStatement.setString(1 , new_password);
                preparedStatement.setString(2 , LoginController.pusername);

                preparedStatement.execute();
            }
            catch (SQLException ex)
            {
                System.err.println("error");
            }
            SignupController.ConfirmationAlert("Password Changed Successfully" , "Confirmation");
            NewPasswordField.setText("");
            ConfirmPasswordField.setText("");
            CurrentPasswordField.setText("");
        }
    }
    @FXML
    public void ProfiletoCartAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("cart-view.fxml" ,event);
    }
    @FXML
    public void ProfiletoPurchasedCourseAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("purchasedcourse-view.fxml" ,event);
    }
    @FXML
    public void ProfiletoHome2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("home2-view.fxml" ,event);
    }
    @FXML
    public void ProfiletoCourse2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("course2-view.fxml" , event);
    }
    @FXML
    public void ProfiletoMentors2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("mentors2-view.fxml" ,event);
    }
    @FXML
    public void ProfiletoAboutus2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("aboutus2-view.fxml" ,event);
    }
    @FXML
    public void ProfileLogoutAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("home-view.fxml" ,event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WelcomeFirstNameLabel.setText(LoginController.pfirstname+"");
        FirstNameLabel.setText(LoginController.pfirstname+"");
        LastNameLabel.setText(LoginController.plastname+"");
        EmailLabel.setText(LoginController.pemail+"");
        UsernameLabel.setText(LoginController.pusername+"");
        FullNameLabel.setText(LoginController.pfirstname+" "+LoginController.plastname);
    }
}
