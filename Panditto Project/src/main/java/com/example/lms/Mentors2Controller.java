package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Mentors2Controller {
    @FXML
    public void Mentors2toHome2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("home2-view.fxml" ,event);
    }
    @FXML
    public void Mentors2toCartAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("cart-view.fxml" ,event);
    }
    @FXML
    public void Mentors2toCourse2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("course2-view.fxml" ,event);
    }
    @FXML
    public void Mentors2toAboutus2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("aboutus2-view.fxml" ,event);
    }
    @FXML
    public void Mentors2toProfileAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("profile-view.fxml" ,event);
    }
    @FXML
    public void Mentors2LogoutAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("mentors-view.fxml" ,event);
    }
}
