package lk.ijse.global_flavour.controller;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import lk.ijse.global_flavour.dto.LoginSetAndGet;
import lk.ijse.global_flavour.model.LoginSetAndGetModel;
import lk.ijse.global_flavour.util.AlertController;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginPageController {

    public static String emailShirePasswordConrollerAndChangePasswordController="";
    public static String adminShireChangePasswordController;
    public static String nameShireChangePasswordController;


    public static String getAdminShireChangePasswordController() {
        return adminShireChangePasswordController;
    }
    public static String getNameShireChangePasswordController() {
        return nameShireChangePasswordController;
    }
    public String getEmail() { return emailShirePasswordConrollerAndChangePasswordController;}

    int i = 0;

    @FXML
    private PasswordField txtLogPassword;

    @FXML
    private JFXComboBox COMAdminCashierlogin;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane loginAncPane;

    @FXML
    private JFXToggleButton showPasswordButton;

    @FXML
    public Button btnShowPassword;

    @FXML
    private TextField txtPassword2;

    @FXML
    void ShowPasswordOnAction(ActionEvent event) {
        /////////////////////////////////////////////////////////////////////////
    }

    @FXML
    void loginPageOnAction(ActionEvent event) throws IOException, AWTException {

        //kumara

        String name = txtUserName.getText();


        String admincashiar = String.valueOf(COMAdminCashierlogin.getValue());
        String admincashiarUserInput = new String();
        String passwordUserInput = new String();
        String nameUserInput = new String();
        String emailUserInput = new String();

        try {
            LoginSetAndGet logSetGet = LoginSetAndGetModel.search(name);
            if (logSetGet != null) {
                admincashiarUserInput=logSetGet.getJobtitel();
                passwordUserInput=logSetGet.getPassword();
                nameUserInput=logSetGet.getUsrname();
                emailUserInput=logSetGet.getEmail();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
        if(txtUserName.getText().isEmpty()&&txtLogPassword.getText().isEmpty()&&COMAdminCashierlogin.getValue()!="Admin"&&COMAdminCashierlogin.getValue()!="Cashier"){
            AlertController.animationMesseagewrong("Error","your not added any data.Please try again!");
        }else {
            if(txtUserName.getText().isEmpty()&&txtLogPassword.getText().isEmpty()){
                AlertController.animationMesseagewrong("Error","Please Enter user name and password!");
            }else if (COMAdminCashierlogin.getValue()!="Admin"&&COMAdminCashierlogin.getValue()!="Cashier"){
                AlertController.animationMesseagewrong("Error","Please Change admin or Cashier!");
            }else {

                    if(admincashiar.equals(admincashiarUserInput)&&txtUserName.getText().equals(nameUserInput)){
                        if(admincashiar.equals(admincashiarUserInput)&&txtUserName.getText().equals(nameUserInput)&&txtLogPassword.getText().equals(passwordUserInput)){
                            if(admincashiar.equals("Admin")){
                                AlertController.notificationBar("SPICY FLAVOUR ","Login Success!");
                                Stage stage = new Stage();
                                Parent root = null;
                                stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
                                stage.setTitle("SPICY FLAVOUR");
                                root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/admindashboard.fxml"));
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                                loginAncPane.getScene().getWindow().hide();
                            }else {
                                Stage stage = new Stage();
                                Parent root = null;
                                stage.setTitle("SPICY FLAVOUR");
                                stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
                                root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/cashierdashboard.fxml"));
                                Scene scene = new Scene(root);
                                stage.setTitle("GLOBAL FLAVOUR");
                                stage.setScene(scene);
                                stage.show();
                                loginAncPane.getScene().getWindow().hide();
                            }
                        }else if(admincashiar.equals(admincashiarUserInput)&&txtUserName.getText().equals(nameUserInput)&&txtLogPassword.getText().isEmpty()){

                            AlertController.animationMesseagewrong("Error","Please Enter password!");
                        }else if(admincashiar.equals(admincashiarUserInput)&&txtUserName.getText().equals(nameUserInput)&&txtLogPassword.getText()!=(passwordUserInput)){
                            i++;

                            if(i==4){
                                i=0;
                                emailShirePasswordConrollerAndChangePasswordController=emailUserInput;
                                adminShireChangePasswordController=admincashiarUserInput;
                                nameShireChangePasswordController=nameUserInput;

                                boolean ok = AlertController.okconfirmmessage("forgot your password. Do you want change password");


                                if(ok){
                                    Stage stage = new Stage();
                                    Parent root = null;
                                    stage.setTitle("SPICY FLAVOUR");
                                    stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
                                    root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/fogotpassworld.fxml"));
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                    loginAncPane.getScene().getWindow().hide();

                                }
                            }else {
                                AlertController.animationMesseagewrong("Error","Wrong Password Please try again!");
                            }

                        }
                    }else {
                        AlertController.animationMesseagewrong("Error","Wrong user Name and Password.Please try again!");
                    }





            }
        }
    }

    public void forgotYourPasswordOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        emailShirePasswordConrollerAndChangePasswordController="";
        adminShireChangePasswordController="";
        nameShireChangePasswordController="";

        Stage stage = new Stage();
        Parent root = null;
        stage.setTitle("SPICY FLAVOUR");
        stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/fogotpassworld.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        loginAncPane.getScene().getWindow().hide();

    }


    public void dontHaveAccountOnActon(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        stage.setTitle("SPICY FLAVOUR");
        stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/createnewaccount.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        loginAncPane.getScene().getWindow().hide();
    }


    @FXML
    void initialize() {
        assert loginAncPane != null : "fx:id=\"loginAncPane\" was not injected: check your FXML file 'loginpage.fxml'.";
        assert txtLogPassword != null : "fx:id=\"txtLogPassword\" was not injected: check your FXML file 'loginpage.fxml'.";
        assert COMAdminCashierlogin != null : "fx:id=\"COMAdminCashierlogin\" was not injected: check your FXML file 'loginpage.fxml'.";
        assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'loginpage.fxml'.";
        COMAdminCashierlogin.getItems().addAll("Admin","Cashier");
        txtPassword2.setVisible(false);

    }

    public void OnMousePassword(MouseEvent mouseEvent) {
        txtLogPassword.setVisible(false);
        txtPassword2.setText(txtLogPassword.getText());
        txtPassword2.setVisible(true);

    }

    public void OnMousePWDExt(MouseEvent mouseEvent) {
        txtLogPassword.setVisible(true);
        txtLogPassword.setText(txtPassword2.getText());
        txtPassword2.setVisible(false);


    }
}
