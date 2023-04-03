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
import javafx.stage.Stage;
import lk.ijse.global_flavour.util.ButtonColourController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CashierDashboardController {

    @FXML
    public JFXButton vehicalBtn1;

    @FXML
    private AnchorPane cashieranchorpane;

    @FXML
    private AnchorPane cashierAncPane;

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
    void btnOnCustom(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/cashiercustomer_form.fxml"));
        cashierAncPane.getChildren().clear();
        cashierAncPane.getChildren().add(load);
        ButtonColourController.btncolor(CustBtn,cashieranchorpane);
    }

    @FXML
    void btnOnDeliver(ActionEvent event) {

    }

    @FXML
    void btnOnEvent(ActionEvent event) {

    }

    @FXML
    void btnOnHome(ActionEvent event) {

    }

    @FXML
    void btnOnItem(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/item_form.fxml"));
        cashierAncPane.getChildren().clear();
        cashierAncPane.getChildren().add(load);
        ButtonColourController.btncolor(ItmBtn,cashieranchorpane);

    }

    @FXML
    void btnOnOrder(ActionEvent event) {

    }


    @FXML
    void BackOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/loginpage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        cashierAncPane.getScene().getWindow().hide();

    }

    @FXML
    public void btnOnVehical(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/cashiervehicle_form.fxml"));
        cashierAncPane.getChildren().clear();
        cashierAncPane.getChildren().add(load);
        ButtonColourController.btncolor(vehicalBtn1,cashieranchorpane);

    }

    @FXML
    void initialize() {
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
