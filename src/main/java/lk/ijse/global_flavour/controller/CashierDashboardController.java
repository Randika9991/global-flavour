package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.global_flavour.util.ButtonColourController;
import lk.ijse.global_flavour.util.TimeAndDateController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CashierDashboardController {


    @FXML
    public JFXButton vehicalBtn1;

    @FXML
    private AnchorPane cashieranchorpane;

    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private JFXButton HomeBtn;

    @FXML
    private JFXButton CustBtn;

    @FXML
    private JFXButton deliverBtn;

    @FXML
    private JFXButton ItmBtn;

    @FXML
    private JFXButton OrderBtn;

    @FXML
    private JFXButton eventBtn;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;

    @FXML
    private JFXButton BtnSupplierLoad;

    @FXML
    void btnOnHome(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/home_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(HomeBtn,adminAncPane);
    }

    @FXML
    void btnOnEvent(ActionEvent event) {

    }

    @FXML
    void btnOnCustom(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/cashiercustomer_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(CustBtn,adminAncPane);
    }

    @FXML
    void btnOnItem(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/item_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(ItmBtn,adminAncPane);
    }

    @FXML
    void btnOnOrder(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/order_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(OrderBtn,adminAncPane);
    }

    @FXML
    void SupplierLoadOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/newsupplyload_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(BtnSupplierLoad,adminAncPane);
    }

    @FXML
    void btnOnDeliver(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/Deliver_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(deliverBtn,adminAncPane);

    }

    @FXML
    public void btnOnVehical(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/cashiervehicle_form.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        ButtonColourController.btncolor(vehicalBtn1,adminAncPane);
    }

    @FXML
    void BackOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        stage.setTitle("SPICY FLAVOUR");
        stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/loginpage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        adminAncPane.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
        TimeAndDateController timeobject = new TimeAndDateController();
        timeobject.timenow(lblTime,lblDate);
    }
}
