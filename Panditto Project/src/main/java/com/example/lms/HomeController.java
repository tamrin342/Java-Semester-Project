package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    public static void sceneChange(String sceneName , ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(HomeController.class.getResource(sceneName));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
  @FXML
    public void HometoCourseAction(ActionEvent event)throws IOException
  {
      sceneChange("course-view.fxml" , event);
  }
    @FXML
    public void HometoMentorsAction(ActionEvent event)throws IOException
    {
        sceneChange("mentors-view.fxml" ,event);
    }
    @FXML
    public void HometoAboutusAction(ActionEvent event)throws IOException
    {
        sceneChange("aboutus-view.fxml" ,event);
    }
  @FXML
    public void HometoLoginAction(ActionEvent event)throws IOException
  {
      sceneChange("login-view.fxml" ,event);
  }
    @FXML
    public void HometoSignupAction(ActionEvent event)throws IOException
    {
        sceneChange("signup-view.fxml" ,event);
    }

}
