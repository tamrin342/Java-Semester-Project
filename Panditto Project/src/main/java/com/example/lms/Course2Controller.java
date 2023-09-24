package com.example.lms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.*;

public class Course2Controller{
    @FXML
    public void EnglishPurchase()
    {
      Purchase("Exclusive English Course (Grammar, Written & Spoken)","Hussna Jalal Ruthila" ,1650);
    }
    @FXML
    public void MathematicsPurchase()
    {
        Purchase("General Math - A Complete Guideline for SSC Students","Ador Zaman" ,2000);
    }
    @FXML
    public void SciencePurchase()
    {
        Purchase("Study Skills Masterclass : A complete Learnign Solution","Kunal Khan" ,1650);
    }
    @FXML
    public void GDPurchase()
    {
        Purchase("Freepik Zero to Hero (Advanced Graphic Design Course)","Khaled Saif" ,700);
    }
    @FXML
    public void WordpressPurchase()
    {
        Purchase("Wordpress Theme & Plugin Customization - Basic to Mastery","Nila Jahan" ,800);
    }
    @FXML
    public void JavascriptPurchase()
    {
        Purchase("Master of Javascript | Basic to Advance","Maruf Hossain" ,6000);
    }
    @FXML
    public void LinkedinPurchase()
    {
        Purchase("Masterclass on Linkedin with Free Digital Tools","Masud Hasan" ,2500);
    }
    @FXML
    public void AmazonPurchase()
    {
        Purchase("A-Z Of Passive Income with Amazon Affiliate Marketing","Shahida Kiron" ,3500);
    }
    @FXML
    public void SEOPurchase()
    {
        Purchase("In-Depth SEO Audit Report for Your Website to Get Success","Mukitul Hasib (Tanzil)" ,5000);
    }

    @FXML
    public void Course2toHome2Action(ActionEvent event) throws IOException
    {
        HomeController.sceneChange("home2-view.fxml" ,event);
    }
    @FXML
    public void Course2toCartAction(ActionEvent event) throws IOException
    {
        HomeController.sceneChange("cart-view.fxml" ,event);
    }
    @FXML
    public void Course2toMentors2Action(ActionEvent event) throws IOException
    {
        HomeController.sceneChange("mentors2-view.fxml" ,event);
    }
    @FXML
    public void Course2toAboutus2Action(ActionEvent event) throws IOException
    {
        HomeController.sceneChange("aboutus2-view.fxml" ,event);
    }
    @FXML
    public void Course2toProfileAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("profile-view.fxml" ,event);
    }
    @FXML
    public void Course2LogoutAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("course-view.fxml" ,event);
    }
    public void Purchase(String ct,String m, double p)
    {
        String course_title = ct;
        String mentor = m;
        double price = p;
        int count =0;

        //checking if already purchased the course
        try {
            String CheckCartQuery = "SELECT * FROM "+LoginController.pusername+";";
            Connection connection = SingletonConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(CheckCartQuery);
            while (resultSet.next()) {
                String PurchasedCourseTitle = resultSet.getString("course_title");

                if(course_title.equals(PurchasedCourseTitle)) {
                    SignupController.WarningAlert("Error" ,"Already Purchased the Course");
                    return;
                }
            }
        }
        catch (SQLException e1)
        {
            System.err.println("SQL EXception 1");
        }

        //checking if already added the course in the cart
        try {
            String CheckCartQuery = "SELECT * FROM cart"+LoginController.pusername+";";
            Connection connection = SingletonConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(CheckCartQuery);
            while (resultSet.next()) {
                String sameCourseTitle = resultSet.getString("course_title");
                String sameMentor = resultSet.getString("mentor");
                double samePrice = resultSet.getDouble("price");

                if(course_title.equals(sameCourseTitle) && mentor.equals(sameMentor) && price==samePrice) {
                    count=1;
                }
            }
        }
        catch (SQLException e1)
        {
            System.err.println("SQL EXception 1");
        }
        if(count==1)
        {
            SignupController.WarningAlert("Already added to cart" ,"Error");
        }
        else
        {   //adding courses to cart if everything's fine
            try {
                String InsertCartQuery = "INSERT INTO cart"+LoginController.pusername+" VALUES('" + course_title + "','" + mentor + "',"+price+");";
                Connection connection = SingletonConnection.getConnection();
                Statement statement = connection.createStatement();
                statement.execute(InsertCartQuery);
            } catch (SQLException ex) {
                System.err.println("SQLException in adding course details into cart");
            }
            SignupController.ConfirmationAlert("Course Added To Cart" , "Confirmation");
        }
    }

}
