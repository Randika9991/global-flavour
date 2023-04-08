package lk.ijse.global_flavour.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static lk.ijse.global_flavour.controller.LoginPageController.getAdminShireChangePasswordController;
import static lk.ijse.global_flavour.controller.LoginPageController.getNameShireChangePasswordController;

public class ChangePasswordController {

    LoginPageController loginPageController=new LoginPageController();

    @FXML
    private AnchorPane changePasswordAnchor;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtConformPassword;

    @FXML
    private Label lblAdminCashiar;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblName1;

    @FXML
    void SaveOnAction(ActionEvent event) {


    }
    @FXML
    void CloseOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/loginpage.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("GLOBAL FLAVOUR");
        stage.setScene(scene);
        stage.show();
        changePasswordAnchor.getScene().getWindow().hide();

    }
    @FXML
    void initialize() {
        lblAdminCashiar.setText(getAdminShireChangePasswordController());
        lblEmail.setText(getNameShireChangePasswordController());
        lblName1.setText(loginPageController.getEmail());


    }

}
