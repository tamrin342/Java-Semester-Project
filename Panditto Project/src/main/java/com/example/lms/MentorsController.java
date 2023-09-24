package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MentorsController {
    //Menu Buttons
    @FXML
    public void MentorstoHomeAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("home-view.fxml" ,event);
    }
    @FXML
    public void MentorstoCourseAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("course-view.fxml" ,event);
    }
    @FXML
    public void MentorstoAboutusAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("aboutus-view.fxml" ,event);
    }
    @FXML
    public void MentorstoLoginAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("login-view.fxml" ,event);
    }
    @FXML
    public void MentorstoSignupAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("signup-view.fxml" ,event);
    }
}
