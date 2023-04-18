package lk.ijse.global_flavour.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.global_flavour.dto.LoginSetAndGet;
import lk.ijse.global_flavour.dto.UserCreateAcountSetAndGet;
import lk.ijse.global_flavour.model.LoginSetAndGetModel;
import lk.ijse.global_flavour.model.UserCreateAcountSetAndGetModel;

import lk.ijse.global_flavour.dto.LoginSetAndGet;
import lk.ijse.global_flavour.model.LoginSetAndGetModel;
import lk.ijse.global_flavour.util.AlertController;

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

        if(cmbAdminCashiar.getValue()!="Admin"&&cmbAdminCashiar.getValue()!="Cashier"&&txtEnterName.getText().isEmpty()&&txtEnteremail.getText().isEmpty()&&txtPassword.getText().isEmpty()&&txtConfirmPassword.getText().isEmpty()){
            AlertController.animationMesseagewrong("Error","Please fill the feald..");

        }else {
            if (cmbAdminCashiar.getValue().equals("Admin") || cmbAdminCashiar.getValue().equals("Cashier")) {
                if (txtEnterName.getText().isEmpty() && txtEnteremail.getText().isEmpty() && txtPassword.getText().isEmpty() && txtConfirmPassword.getText().isEmpty()) {
                    AlertController.animationMesseagewrong("Error","Please fill the other feald..");
                    return;

                } else {
                    if (txtEnteremail.getText().isEmpty() && txtPassword.getText().isEmpty() && txtConfirmPassword.getText().isEmpty()) {
                        AlertController.animationMesseagewrong("Error","Please Enter email and Password");
                        return;
                    } else {
                        if (txtPassword.getText().isEmpty() && txtConfirmPassword.getText().isEmpty()) {
                            AlertController.animationMesseagewrong("Error","Please Enter Password");
                            return;
                        } else {
                            if (txtConfirmPassword.getText().isEmpty()) {
                                AlertController.animationMesseagewrong("Error","Please Confirm your password");
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
                                        AlertController.animationMesseagewrong("Error","something happened!");
                                    }

                                    if(admincashiarUserInput.equals(cmbAdminCashiar.getValue())&&nameUserInput.equals(txtEnterName.getText())&&emailUserInput.equals(txtEnteremail.getText())){
                                        AlertController.animationMesseagewrong("Error","Already Create this Account . try again");

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
                                                AlertController.animationMesseageCorect("CONFIRMATION","Account Created !");
                                            }
                                        } catch (SQLException e) {
                                            System.out.println(e);

                                            AlertController.animationMesseagewrong("Error","something went wrong!");
                                        }
                                    }


                                } else {

                                    AlertController.animationMesseagewrong("Error","Please Try your Confirm password");
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

    }

    @FXML
    void buttonOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        stage.setTitle("SPICY FLAVOUR");
        stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
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
