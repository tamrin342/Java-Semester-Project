package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CourseController {
    @FXML
    public void CoursetoHomeAction(ActionEvent event) throws IOException
    {
        HomeController.sceneChange("home-view.fxml" ,event);
    }
    @FXML
    public void CoursetoMentorsAction(ActionEvent event) throws IOException
    {
        HomeController.sceneChange("mentors-view.fxml" ,event);
    }
    @FXML
    public void CoursetoAboutusAction(ActionEvent event) throws IOException
    {
        HomeController.sceneChange("aboutus-view.fxml" ,event);
    }
    @FXML
    public void CoursetoLoginAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("login-view.fxml" ,event);
    }
    @FXML
    public void CoursetoSignupAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("signup-view.fxml" ,event);
    }
}
