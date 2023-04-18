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
import lk.ijse.global_flavour.dto.ChangePassword;
import lk.ijse.global_flavour.dto.Item;
import lk.ijse.global_flavour.model.ChangePasswordModel;
import lk.ijse.global_flavour.model.ItemModel;
import lk.ijse.global_flavour.util.AlertController;

import java.io.IOException;
import java.sql.SQLException;

import static lk.ijse.global_flavour.controller.LoginPageController.getAdminShireChangePasswordController;
import static lk.ijse.global_flavour.controller.LoginPageController.getNameShireChangePasswordController;



public class ChangePasswordController {

    LoginPageController loginPageController=new LoginPageController();
    FogotYourPasswordController fogotYourPasswordController= new FogotYourPasswordController();

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

        if(fogotYourPasswordController.getEmailShireChangePasswordController2().isEmpty()){
            String userName = getNameShireChangePasswordController();
            String empId = null;
            String Password = txtPassword.getText();
            String email = loginPageController.getEmail();
            String jobTittle = getAdminShireChangePasswordController();

            if(txtPassword.getText().isEmpty()&&txtConformPassword.getText().isEmpty()){
                AlertController.animationMesseagewrong("Error","Please Enter Password!");
            }else {
                if(txtPassword.getText().isEmpty()){
                    AlertController.animationMesseagewrong("Error","Please Enter new Password!");
                }else if(txtConformPassword.getText().isEmpty()){
                    AlertController.animationMesseagewrong("Error","Please Enter Your ConformPassword!");
                }else {
                    if(txtPassword.getText().equals(txtConformPassword.getText())){
                        ChangePassword itemAll = new ChangePassword(userName, empId, Password,email,jobTittle);

                        try {
                            boolean isUpdated = ChangePasswordModel.update(itemAll);
                            AlertController.animationMesseageCorect("CONFIRMATION","User Password Updated!");
                            //onActionGetAllItem();
                        } catch (SQLException e) {

                            AlertController.animationMesseagewrong("Error","something went wrong!");
                        }
                    }else {
                        AlertController.animationMesseagewrong("Error","Wrong ConformPassword. Please Try again!");
                    }

                }
            }
        }else {
            String userName = fogotYourPasswordController.getNameShireChangePasswordController2();
            String empId = null;
            String Password = txtPassword.getText();
            String email = FogotYourPasswordController.getEmailShireChangePasswordController2();
            String jobTittle = FogotYourPasswordController.getAdminShireChangePasswordController2();

            if(txtPassword.getText().isEmpty()&&txtConformPassword.getText().isEmpty()){
                AlertController.animationMesseagewrong("Error","Please Enter Password!");
            }else {
                if(txtPassword.getText().isEmpty()){
                    AlertController.animationMesseagewrong("Error","Please Enter new Password!");
                }else if(txtConformPassword.getText().isEmpty()){
                    AlertController.animationMesseagewrong("Error","Please Enter Your ConformPassword!");
                }else {
                    if(txtPassword.getText().equals(txtConformPassword.getText())){
                        ChangePassword itemAll = new ChangePassword(userName, empId, Password,email,jobTittle);

                        try {
                            boolean isUpdated = ChangePasswordModel.update(itemAll);
                            AlertController.animationMesseageCorect("CONFIRMATION","User Password Updated!");
                            //onActionGetAllItem();
                        } catch (SQLException e) {

                            AlertController.animationMesseagewrong("Error","something went wrong!");
                        }
                    }else {
                        AlertController.animationMesseagewrong("Error","Wrong ConformPassword. Please Try again!");
                    }

                }
            }
        }






    }
    @FXML
    void CloseOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        stage.setTitle("SPICY FLAVOUR");
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/loginpage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        changePasswordAnchor.getScene().getWindow().hide();

    }
    @FXML
    void initialize() {

        if(fogotYourPasswordController.getEmailShireChangePasswordController2().isEmpty()){
            lblAdminCashiar.setText(getAdminShireChangePasswordController());
            lblEmail.setText(getNameShireChangePasswordController());
            lblName1.setText(loginPageController.getEmail());
        }else {
            lblAdminCashiar.setText(FogotYourPasswordController.getAdminShireChangePasswordController2());
            lblEmail.setText(FogotYourPasswordController.getNameShireChangePasswordController2());
            lblName1.setText(FogotYourPasswordController.getEmailShireChangePasswordController2());

        }
    }

}
