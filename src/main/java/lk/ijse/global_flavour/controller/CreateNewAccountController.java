package lk.ijse.global_flavour.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.global_flavour.dto.LoginSetAndGet;
import lk.ijse.global_flavour.dto.UserCreateAcountSetAndGet;
import lk.ijse.global_flavour.model.LoginSetAndGetModel;
import lk.ijse.global_flavour.model.UserCreateAcountSetAndGetModel;

import lk.ijse.global_flavour.dto.LoginSetAndGet;
import lk.ijse.global_flavour.model.LoginSetAndGetModel;

import java.io.IOException;
import java.sql.SQLException;

public class CreateNewAccountController {

    public TextField txtEnterName1;
    public TextField txtPassword2;
    public TextField txtConfirmPassword2;
    public Button btnSPSWEnt;
    public Button btnSPSConform;


    @FXML
    private ComboBox cmbAdminCashiar;

    @FXML
    private TextField txtEnterName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEnteremail;

    @FXML
    private AnchorPane txtcreateAcount;

    public void CreateOnAction(ActionEvent actionEvent) throws IOException {

            if (cmbAdminCashiar.getValue().equals("Admin") || cmbAdminCashiar.getValue().equals("Cashier")) {
                if (txtEnterName.getText().isEmpty() && txtEnteremail.getText().isEmpty() && txtPassword.getText().isEmpty() && txtConfirmPassword.getText().isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "Please fill the other feald..").show();

                    return;

                } else {
                    if (txtEnteremail.getText().isEmpty() && txtPassword.getText().isEmpty() && txtConfirmPassword.getText().isEmpty()) {
                        new Alert(Alert.AlertType.ERROR, "Please Enter email and Password").show();
                        return;
                    } else {
                        if (txtPassword.getText().isEmpty() && txtConfirmPassword.getText().isEmpty()) {
                            new Alert(Alert.AlertType.ERROR, "Please Enter Password").show();
                            return;
                        } else {
                            if (txtConfirmPassword.getText().isEmpty()) {
                                new Alert(Alert.AlertType.ERROR, "Please Confirm your password").show();
                                return;
                            } else {
                                if (txtPassword.getText().equals(txtConfirmPassword.getText())) {


                                    String name = txtEnterName.getText();
                                    String admincashiarUserInput = new String();
                                    String nameUserInput = new String();
                                    String emailUserInput = new String();

                                    try {
                                        LoginSetAndGet logSetGet = LoginSetAndGetModel.search(name);
                                        if (logSetGet != null) {
                                            admincashiarUserInput = logSetGet.getJobtitel();
                                            nameUserInput = logSetGet.getUsrname();
                                            emailUserInput= logSetGet.getEmail();
                                        }
                                    } catch (SQLException e) {
                                        new Alert(Alert.AlertType.ERROR, "something happened!").show();
                                    }

                                    if(admincashiarUserInput.equals(cmbAdminCashiar.getValue())&&nameUserInput.equals(txtEnterName.getText())&&emailUserInput.equals(txtEnteremail.getText())){
                                        new Alert(Alert.AlertType.ERROR, "Already Create this Account . try again").show();
                                        txtConfirmPassword.setText("");

                                        txtEnterName.setText("");
                                        txtPassword.setText("");
                                        txtConfirmPassword.setText("");
                                        txtEnteremail.setText("");

                                    }else {
                                        String nameInput = txtEnterName.getText();
                                        String password = txtPassword.getText();
                                        String email = txtEnteremail.getText();
                                        String tittle = String.valueOf(cmbAdminCashiar.getValue());

                                        UserCreateAcountSetAndGet cus = new UserCreateAcountSetAndGet(nameInput, password, email, tittle);

                                        try {
//            boolean isSaved = ItemModel.save(code, description, unitPrice, qtyOnHand);
                                            boolean isSaved = UserCreateAcountSetAndGetModel.save(cus);
                                            if (isSaved) {
                                                new Alert(Alert.AlertType.CONFIRMATION, "Account Created !").show();
                                            }
                                        } catch (SQLException e) {
                                            System.out.println(e);
                                            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                                        }
                                    }


                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Please Try your Confirm password").show();
                                    txtConfirmPassword.setText("");
                                    return;
                                }

                            }
                        }
                    }

                }
            } else {
                return;

            }

    }

    @FXML
    void buttonOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/loginpage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        txtcreateAcount.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
        assert txtcreateAcount != null : "fx:id=\"txtcreateAcount\" was not injected: check your FXML file 'createnewaccount.fxml'.";
        assert txtEnterName != null : "fx:id=\"txtEnterName\" was not injected: check your FXML file 'createnewaccount.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'createnewaccount.fxml'.";
        assert txtConfirmPassword != null : "fx:id=\"txtConfirmPassword\" was not injected: check your FXML file 'createnewaccount.fxml'.";
        assert txtEnteremail != null : "fx:id=\"txtEnteremail\" was not injected: check your FXML file 'createnewaccount.fxml'.";
        assert cmbAdminCashiar != null : "fx:id=\"cmbAdminCashiar\" was not injected: check your FXML file 'createnewaccount.fxml'.";

        cmbAdminCashiar.getItems().addAll("Admin","Cashier");
        txtPassword2.setVisible(false);
        txtConfirmPassword2.setVisible(false);
    }

    public void ShowPasswordEntOnAction(ActionEvent actionEvent) {
    }

    public void ShowConformPasswordOnAction(ActionEvent actionEvent) {
    }

    public void OnMouseEnterPWD(MouseEvent mouseEvent){
        txtPassword.setVisible(false);
        txtPassword2.setText(txtPassword.getText());
        txtPassword2.setVisible(true);
    }

    public void OnMouseEnterPWDExt(MouseEvent mouseEvent){
        txtPassword.setVisible(true);
        txtPassword.setText(txtPassword2.getText());
        txtPassword2.setVisible(false);
    }

    public void OnMouseConfrmPWD(MouseEvent mouseEvent) {
        txtConfirmPassword.setVisible(false);
        txtConfirmPassword2.setText(txtConfirmPassword.getText());
        txtConfirmPassword2.setVisible(true);
    }

    public void OnMouseConfrmPWDExt(MouseEvent mouseEvent) {
        txtConfirmPassword.setVisible(true);
        txtConfirmPassword.setText(txtConfirmPassword2.getText());
        txtConfirmPassword2.setVisible(false);
    }
}
