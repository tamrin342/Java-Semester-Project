package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Aboutus2Controller {
    @FXML
    public void Aboutus2toCartAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("cart-view.fxml" ,event);
    }
    @FXML
    public void Aboutus2toHome2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("home2-view.fxml" ,event);
    }
    @FXML
    public void Aboutus2toCourse2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("course2-view.fxml" ,event);
    }
    @FXML
    public void Abouttus2toMentors2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("mentors2-view.fxml" ,event);
    }
    @FXML
    public void Aboutus2toProfileAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("profile-view.fxml" ,event);
    }
    @FXML
    public void Aboutus2LogoutAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("aboutus-view.fxml" ,event);
    }
}
