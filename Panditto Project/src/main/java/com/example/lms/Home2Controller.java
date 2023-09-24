package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Home2Controller {
    @FXML
    public void Home2toCourse2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("course2-view.fxml" , event);
    }
    @FXML
    public void Home2toCartAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("cart-view.fxml" , event);
    }
    @FXML
    public void Home2toMentors2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("mentors2-view.fxml" ,event);
    }
    @FXML
    public void Home2toAboutus2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("aboutus2-view.fxml" ,event);
    }
    @FXML
    public void Home2toProfileAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("profile-view.fxml" ,event);
    }
    @FXML
    public void Home2LogoutAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("home-view.fxml" ,event);
    }

}
