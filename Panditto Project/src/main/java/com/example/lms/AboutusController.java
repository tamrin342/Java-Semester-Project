package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.io.IOException;

public class AboutusController {
    @FXML
    public void AboutustoHomeAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("home-view.fxml" ,event);
    }
    @FXML
    public void AboutustoCourseAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("course-view.fxml" ,event);
    }
    @FXML
    public void AbouttustoMentorsAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("mentors-view.fxml" ,event);
    }
    @FXML
    public void AboutustoLoginAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("login-view.fxml" ,event);
    }
    @FXML
    public void AboutustoSignupAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("signup-view.fxml" ,event);
    }
}
