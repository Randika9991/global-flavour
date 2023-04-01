package lk.ijse.global_flavour.controller;


import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import lk.ijse.global_flavour.dto.LoginSetAndGet;
import lk.ijse.global_flavour.model.LoginSetAndGetModel;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class LoginPageController {
    @FXML
    private PasswordField txtLogPassword;

    @FXML
    private JFXComboBox COMAdminCashierlogin;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane loginAncPane;

    @FXML
    private TextField txtLoginUserName;

    @FXML
    void loginPageOnAction(ActionEvent event) throws IOException{
        String name = txtUserName.getText();

        String admincashiar = String.valueOf(COMAdminCashierlogin.getValue());
        String admincashiarUserInput = new String();
        String passwordUserInput = new String();
        String nameUserInput = new String();

        try {
            LoginSetAndGet logSetGet = LoginSetAndGetModel.search(name);
            if (logSetGet != null) {
                admincashiarUserInput=logSetGet.getJobtitel();
                passwordUserInput=logSetGet.getPassword();
                nameUserInput=logSetGet.getUsrname();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
        if(admincashiar.equals(admincashiarUserInput)){
            if(txtUserName.getText().equals(nameUserInput)){
                if(txtLogPassword.getText().equals(passwordUserInput)){
                    if(admincashiar.equals("Admin")){
                        Stage stage = new Stage();
                        Parent root = null;
                        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/admindashboard.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        loginAncPane.getScene().getWindow().hide();
                    }else {
                        Stage stage = new Stage();
                        Parent root = null;
                        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/cashierdashboard.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        loginAncPane.getScene().getWindow().hide();
                    }
                }else if(txtLogPassword.getText().isEmpty()){
                    new Alert(Alert.AlertType.ERROR, "Please Enter Password! ").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Fogot your Password! Change password or Create new Account").show();
                }
            }else if (nameUserInput.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "can't find Youser name or password.").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Pleaes Enter Corret name.").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Please Change Admin or Cashiar, can't find Youser name or password ").show();
        }
    }

    public void forgotYourPasswordOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/fogotpassworld.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        loginAncPane.getScene().getWindow().hide();

    }

    public void dontHaveAccountOnActon(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
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
    }
}
