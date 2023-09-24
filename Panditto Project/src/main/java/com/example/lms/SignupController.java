package com.example.lms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.*;

public class SignupController{
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;
    @FXML
    private TextField EmailField;
    @FXML
    private TextField UserNameField;
    @FXML
    private PasswordField SPasswordField;
    @FXML
    private Label SuccessSigninLabel;
    @FXML
    public void SignupAction(ActionEvent event)
    {
        String firstname = FirstNameField.getText();
        String lastname = LastNameField.getText();
        String email = EmailField.getText();
        String username = UserNameField.getText();
        String password = SPasswordField.getText();
        //If Username is same
        try {
            String Query = "SELECT * FROM signupform;";
            Connection connection = SingletonConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query);
            while (resultSet.next()) {
                String sameusername = resultSet.getString("User_Name");
                if(username.equals(sameusername)) {
                    WarningAlert("Change Username" ,"Username Already Exists");
                    return;
                }
            }
        }
        catch (SQLException e1)
        {
            System.err.println("SQL EXception 1");
        }

        if(firstname.equals("") || lastname.equals("") || email.equals("") || username.equals("") || password.equals(""))
        {
            ErrorAlert("One of the Field is Empty!" ,"Error");
        }
            else {

//Inserting Values in Database if everything's fine
            try {
                String InsertQuery = "INSERT INTO signupform VALUES('" + firstname + "','" + lastname + "','" + email + "','" + username + "','" + password + "');";
                Connection connection = SingletonConnection.getConnection();
                Statement statement = connection.createStatement();
                statement.execute(InsertQuery);
            } catch (SQLException ex) {
                System.err.println("SQLException");
            }

            SuccessSigninLabel.setText("Account Created Successfully!");
            FirstNameField.setText("");
            LastNameField.setText("");
            EmailField.setText("");
            UserNameField.setText("");
            SPasswordField.setText("");
            //creating specific table for the user
            try {
                String UserTableQuery = "CREATE Table "+username+" (course_title varchar(400) ,mentor varchar(100));";
                Connection connection = SingletonConnection.getConnection();
                Statement statement = connection.createStatement();
                statement.execute(UserTableQuery);
            } catch (SQLException ex) {
                System.err.println("SQLException");
            }
            //creating specific carttable for the user
            try {
                String UserTableQuery = "CREATE Table cart"+username+" (course_title varchar(400) ,mentor varchar(100),price double);";
                Connection connection = SingletonConnection.getConnection();
                Statement statement = connection.createStatement();
                statement.execute(UserTableQuery);
            } catch (SQLException ex) {
                System.err.println("SQLException");
            }
            //inseritng profile values in user profile table
//            try {
//                String ProfileInfoQuery = "INSERT INTO "+username+" VALUES('" + firstname + "','" + lastname + "','" + email + "','" + username + "','" + password + "');";
//                Connection connection = SingletonConnection.getConnection();
//                Statement statement = connection.createStatement();
//                statement.execute(ProfileInfoQuery);
//            } catch (SQLException ex) {
//                System.err.println("SQLException");
//            }
        }

    }
    @FXML
    public void RemovingSuccessLabel(){
        SuccessSigninLabel.setText("");
    }

@FXML
    public void SignuptoLoginAction(ActionEvent event) throws IOException
{
    HomeController.sceneChange("login-view.fxml",event);
}
    @FXML
    public void SignuptoHomeAction(ActionEvent event) throws IOException
    {
        HomeController.sceneChange("home-view.fxml",event);
    }
    public static void ErrorAlert(String ContentText, String HeaderText)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(ContentText);
        alert.setHeaderText(HeaderText);
        alert.showAndWait();
    }
    public static void ConfirmationAlert(String ContentText, String HeaderText)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(ContentText);
        alert.setHeaderText(HeaderText);
        alert.showAndWait();
    }
    public static void WarningAlert(String ContentText, String HeaderText)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(ContentText);
        alert.setHeaderText(HeaderText);
        alert.showAndWait();
    }

}
