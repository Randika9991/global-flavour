package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lk.ijse.global_flavour.util.ButtonColourController;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminDashboardController {

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;
    @FXML
    private Label txtDate;
    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private AnchorPane EmpAnchorpane;


    @FXML
    private JFXButton EmpBtn;

    @FXML
    private JFXButton HomeBtn;

    @FXML
    private JFXButton SupBtn;

    @FXML
    private JFXButton ItmBtn;

    @FXML
    private JFXButton OrderBtn;

    @FXML
    private JFXButton SalaryBtn1;

    @FXML
    private JFXButton RepotBtn;

    @FXML
    public void btnOnHome(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/home_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
    }

    @FXML
    public void btnOnEmployee(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/employee_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(EmpBtn,adminAncPane);
    }

    public void btnOnSuppliers(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/suppliers_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(SupBtn,adminAncPane);
    }

    public void btnOnItem(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/item_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(ItmBtn,adminAncPane);
    }

    public void btnOnOrder(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/order_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(ItmBtn,adminAncPane);

    }

    @FXML
    void btnOnSalary(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/adminsalary_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(SalaryBtn1,adminAncPane);
    }

    public void btnOnReport(ActionEvent actionEvent) {

    }
    @FXML
    void BackOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/loginpage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        adminAncPane.getScene().getWindow().hide();
    }


    @FXML
    void initialize() {
        assert adminAncPane != null : "fx:id=\"adminAncPane\" was not injected: check your FXML file 'admindashboard.fxml'.";
        assert txtDate != null : "fx:id=\"txtDate\" was not injected: check your FXML file 'admindashboard.fxml'.";

        Timenow();
    }
    public void Timenow(){
        Thread thread =new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM,  dd, yyyy");
            while (true){
                try{
                    Thread.sleep(1000);

                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                String timenow1 = sdf1.format(new Date());

                Platform.runLater(() ->{
                    lblTime.setText(timenow);
                    lblDate.setText(timenow1);
                });
            }
        });
        thread.start();
    }


}

