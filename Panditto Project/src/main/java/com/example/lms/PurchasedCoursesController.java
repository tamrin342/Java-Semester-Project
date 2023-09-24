package com.example.lms;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class PurchasedCoursesController implements Initializable {
    @FXML
    private TableView<Purchase> purchaseTableView;
    @FXML
    private TableColumn<Purchase, String> PurchaseColumn;
    @FXML
    private TableColumn<Purchase, String> PurchaseMentorColumn;
    @FXML
    private Label WelcomeFirstNameLabel;
    ObservableList<Purchase> purchaseObservableList;
    @FXML
    public void PurchasetoCartAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("cart-view.fxml" ,event);
    }
    @FXML
    public void PurchasedtoInfoAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("profile-view.fxml" ,event);
    }
    @FXML
    public void PurchasetoHome2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("home2-view.fxml" ,event);
    }
    @FXML
    public void PurchasetoCourse2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("course2-view.fxml" , event);
    }
    @FXML
    public void PurchasetoMentors2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("mentors2-view.fxml" ,event);
    }
    @FXML
    public void PurchasetoAboutus2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("aboutus2-view.fxml" ,event);
    }
    @FXML
    public void PurchaseLogoutAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("home-view.fxml" ,event);
    }
    public void DeleteCourseAction()
    {
        //if user deletes any purchased course permanently
        int selectedIndex = purchaseTableView.getSelectionModel().getSelectedIndex();
        String course_title = PurchaseColumn.getCellData(selectedIndex);

        try {
            String deleteQuery = "DELETE FROM "+LoginController.pusername+" WHERE course_title = ?";
            Connection connection = SingletonConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, course_title);
            preparedStatement.execute();

            readuserDatabase();
        } catch (SQLException ex) {
            System.err.println("Failed to delete from database");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     //getting username to use after welcome text
        WelcomeFirstNameLabel.setText(LoginController.pfirstname+"");
       // initializing users purchased courses in the table
        purchaseObservableList = FXCollections.observableArrayList();
        purchaseTableView.setItems(purchaseObservableList);

        PurchaseColumn.setCellValueFactory(cellDta -> new SimpleStringProperty(cellDta.getValue().getPurchased_course()));
        PurchaseMentorColumn.setCellValueFactory(cellDta -> new SimpleStringProperty(cellDta.getValue().getPurchased_mentor()));

        readuserDatabase();
    }

    private void readuserDatabase() {
        try {
            purchaseObservableList.clear();
            String query = "SELECT * FROM "+LoginController.pusername+";";
            Connection connection = SingletonConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String CCcourse_title = resultSet.getString("course_title");
                String CCmentor = resultSet.getString("mentor");

                Purchase purchase = new Purchase(CCcourse_title,CCmentor);
                purchaseObservableList.add(purchase);
            }
        } catch (SQLException ex) {
            System.err.println("Failed to connect database in PurchasedCoursesController");
        }
    }
}
