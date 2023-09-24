package com.example.lms;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CartController implements Initializable{
        @FXML
        private TableView<Course> CourseTableView ;
        @FXML
        private TableColumn<Course,String> CourseTitleColumn ;
        @FXML
        private TableColumn<Course,String> MentorColumn ;
        @FXML
        private TableColumn<Course,Number> PriceColumn ;
        @FXML
        private Label successLabel,itemCount,SalesTaxLabel,SubtotalLabel,GrandTotalLabel,MinusForCoupon;
        @FXML
        private TextField PaymentField;
        ObservableList<Course> courseObservableList ;
        @FXML
        private ChoiceBox<String> choiceBox;
        private String[] CouponCode = {"SHADHIN325","HAPPYSHOPPING250" ,"TOPLEARNER500","BEAST777"};


    @FXML
        public void ProccedtoPay() {
        //checking if the cart is empty
        int count = 0;
        try {
            String CountSubtotalQuery = "SELECT * FROM cart" + LoginController.pusername + ";";
            Connection connection = SingletonConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(CountSubtotalQuery);

            while (resultSet.next()) {
                count = 1;
                break;
            }
        } catch (SQLException ex) {
            System.err.println("Failed to connect database in CartController");
        }
        if (count == 0) {
            SignupController.ErrorAlert("Error", "The Cart Is Empty");
        } else {
            //will purchase only when the user will enter the right grandtotal amount
            if (PaymentField.getText().isEmpty()) {
                SignupController.ErrorAlert("Enter the Grand Total Amount", "Amount Is Empty");
            } else {

                double Amount = Double.parseDouble(PaymentField.getText());
                double GT = Double.parseDouble(GrandTotalLabel.getText());

                if (Amount == GT) {
                    try {
                        //sending all the rows from cart tp purchased courses
                        String CopytoPurchaseQuery = "INSERT INTO " + LoginController.pusername + "(course_title , mentor) SELECT course_title,mentor from cart" + LoginController.pusername + ";";
                        Connection connection = SingletonConnection.getConnection();
                        Statement statement = connection.createStatement();
                        statement.execute(CopytoPurchaseQuery);

                    } catch (SQLException ex) {
                        System.err.println("SQLException in adding purchased courses");
                    }

//after inserting all the cart courses in purchased courses , these should be deleted from database too
                    try {
                        String deleteCartQuery = "DELETE FROM cart" + LoginController.pusername + ";";
                        Connection connection = SingletonConnection.getConnection();
                        Statement statement = connection.createStatement();
                        statement.execute(deleteCartQuery);
                    } catch (SQLException ex) {
                        System.err.println("SQL exception in deleting Cart");
                    }
                    SignupController.ConfirmationAlert("Thanks for Purchasing", "Enjoy Our Course");
                    courseObservableList.clear();
                    SubtotalLabel.setText("");
                    GrandTotalLabel.setText("");
                    SalesTaxLabel.setText("");
                    MinusForCoupon.setText("");
                    PaymentField.setText("");
                } else {
                    if (Amount < GT) {
                        double need = GT - Amount;
                        SignupController.ErrorAlert("Error", "More BDT " + need + " Needed");
                    } else {
                        double need = Amount - GT;
                        SignupController.ErrorAlert("Error", "BDT " + need + " is Extra");

                    }
                }
            }
        }
    }

        @FXML
        public void RemoveFromCartAction() {
            int selectedIndex = CourseTableView.getSelectionModel().getSelectedIndex();
            String course_title = CourseTitleColumn.getCellData(selectedIndex);

            try {
                String deleteQuery = "DELETE FROM cart" + LoginController.pusername + " WHERE course_title = ?";
                Connection connection = SingletonConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setString(1, course_title);
                preparedStatement.execute();

                readCourseDatabase();
            } catch (SQLException ex) {
                System.err.println("Failed to delete from database");
            }
//after removing counting labels should be updated
            SubtotalLabel.setText(CountSubtotal() + "");
            itemCount.setText(CountItem() + "");
            int countTax = CountItem() * 60;
            SalesTaxLabel.setText(countTax + "");
            GrandTotalLabel.setText((CountSubtotal() + countTax) + "");


            String code = choiceBox.getValue();
            if(code==null){
                double GT = CountSubtotal() + CountItem() * 60;
                GrandTotalLabel.setText(GT + "");
            }
            else {
                if (code.equals("SHADHIN325")) {
                    double GT = CountSubtotal() + CountItem() * 60 - 325;
                    GrandTotalLabel.setText(GT + "");
                }

                if (code.equals("HAPPYSHOPPING250")) {
                    double GT = CountSubtotal() + CountItem() * 60 - 250;
                    GrandTotalLabel.setText(GT + "");
                }

                if (code.equals("TOPLEARNER500")) {
                    double GT = CountSubtotal() + CountItem() * 60 - 500;
                    GrandTotalLabel.setText(GT + "");
                }

                if (code.equals("BEAST777")) {
                    double GT = CountSubtotal() + CountItem() * 60 - 777;
                    GrandTotalLabel.setText(GT + "");
                }
            }
//when cart is empty still MinusForCoupon Label displays the amount and grandtotal so resetting the labels
                if (CountSubtotal() == 0) {
                    MinusForCoupon.setText("");
                    GrandTotalLabel.setText("0.0");
                }
            }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle){
        //tableview initialize
            courseObservableList = FXCollections.observableArrayList();
            CourseTableView.setItems(courseObservableList);

            //specifying each column in the table
            CourseTitleColumn.setCellValueFactory(cellDta -> new SimpleStringProperty(cellDta.getValue().getCourse_title()));
            MentorColumn.setCellValueFactory(cellDta -> new SimpleStringProperty(cellDta.getValue().getMentor()));
            PriceColumn.setCellValueFactory(cellDta -> new SimpleDoubleProperty(cellDta.getValue().getPrice()));

            //to load priveous values if the table has some
            readCourseDatabase();
            //Choicebox initialize
            choiceBox.getItems().addAll(CouponCode);
            choiceBox.setOnAction(this::getCode);

            SubtotalLabel.setText(CountSubtotal()+"");
            itemCount.setText(CountItem()+"");
            int countTax = CountItem()*60;
            SalesTaxLabel.setText(countTax+"");
            double Gtotal = CountSubtotal()+CountItem()*60;
            GrandTotalLabel.setText(Gtotal+"");

        }
        public void getCode(ActionEvent event) {
            String CouponCode = choiceBox.getValue();
            //if user doesnt use any coupon then no need to execute this method
            if(CouponCode.equals(null))
            {
                return;
            }
            if (CouponCode.equals("SHADHIN325")) {
                MinusForCoupon.setText("-325");
                double GrandTotal = CountSubtotal()+ CountItem()*60 - 325;
                GrandTotalLabel.setText(GrandTotal+"");
            }
            if (CouponCode.equals("HAPPYSHOPPING250")) {
                MinusForCoupon.setText("-"+250);
                double GrandTotal = CountSubtotal()+ CountItem()*60 - 250;
                GrandTotalLabel.setText(GrandTotal+"");
            }
            if (CouponCode.equals("TOPLEARNER500")) {
                MinusForCoupon.setText("-500");
                double GrandTotal = CountSubtotal()+ CountItem()*60 - 500;
                GrandTotalLabel.setText(GrandTotal+"");
            }
            if (CouponCode.equals("BEAST777")) {
                MinusForCoupon.setText("-777");
                double GrandTotal = CountSubtotal()+ CountItem()*60 - 777;
                GrandTotalLabel.setText(GrandTotal+"");
            }
            if(CountSubtotal()==0)
            {
                MinusForCoupon.setText("");
                GrandTotalLabel.setText("0.0");
            }

        }

    public double CountSubtotal() {
        double countSubtotal = 0;
        try {
            String CountSubtotalQuery = "SELECT * FROM cart" + LoginController.pusername + ";";
            Connection connection = SingletonConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(CountSubtotalQuery);

            while (resultSet.next()) {
                //reading the prices to count
                double CCprice = resultSet.getDouble("price");
                countSubtotal = countSubtotal+CCprice;
            }
        } catch (SQLException ex) {
            System.err.println("Failed to connect database in CartController");
        }
        return countSubtotal;
    }
    public int CountItem() {
        int countItem = 0;
        try {
            //reading how many courses are displaying in the cart
            String CountSubtotalQuery = "SELECT * FROM cart" + LoginController.pusername + ";";
            Connection connection = SingletonConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(CountSubtotalQuery);

            while (resultSet.next()) {
                countItem++;
            }
        } catch (SQLException ex) {
            System.err.println("Failed to connect database in CartController");
        }
        return countItem;
    }
    private void readCourseDatabase() {
        try {
            courseObservableList.clear();
            String query = "SELECT * FROM cart"+LoginController.pusername+";";
            Connection connection = SingletonConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String CCcourse_title = resultSet.getString("course_title");
                String CCmentor = resultSet.getString("mentor");
                double CCprice = resultSet.getDouble("price");

                Course course = new Course(CCcourse_title,CCmentor,CCprice);
                courseObservableList.add(course);
            }
        } catch (SQLException ex) {
            System.err.println("Failed to connect database in CartController");
        }
    }

    @FXML
    public void CarttoHome2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("home2-view.fxml" ,event);
    }
    @FXML
    public void CarttoMentors2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("mentors2-view.fxml" ,event);
    }
    @FXML
    public void CarttoCourse2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("course2-view.fxml" ,event);
    }
    @FXML
    public void CarttoAboutus2Action(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("aboutus2-view.fxml" ,event);
    }
    @FXML
    public void CarttoProfileAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("profile-view.fxml" ,event);
    }
    @FXML
    public void CartLogoutAction(ActionEvent event)throws IOException
    {
        HomeController.sceneChange("mentors-view.fxml" ,event);
    }
    }
