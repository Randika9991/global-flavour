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
import lk.ijse.global_flavour.dto.*;
import lk.ijse.global_flavour.model.*;
import lk.ijse.global_flavour.model.CreateNewAccountModel;

import lk.ijse.global_flavour.dto.LoginSetAndGet;
import lk.ijse.global_flavour.model.LoginSetAndGetModel;
import lk.ijse.global_flavour.util.AlertController;
import lk.ijse.global_flavour.util.ValidateField;

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

    @FXML
    private Label lblInvalidEmail;

    @FXML
    private Label lblAlredyAddName;

    @FXML
    private Label lblAlredyAddEmail;

    String nameUserInputPart2 = new String();
    String emailUserInputPart2 = new String();

    @FXML
    void buttonCheckNameOnACT(ActionEvent event) {
        if(txtEnterName.getText().isEmpty()){
            AlertController.animationMesseagewrong("Error","Please Enter your name.");

        }else {
            String name = txtEnterName.getText();
            String nameUserInput = new String();

            try {
                LoginSetAndGet logSetGet = LoginSetAndGetModel.search(name);
                if (logSetGet != null) {
                    nameUserInput = logSetGet.getUsrname();
                }
            } catch (SQLException e) {
                AlertController.animationMesseagewrong("Error","something happened!");
            }
            if(nameUserInput.equals(txtEnterName.getText())){
                lblAlredyAddName.setVisible(true);
            }else {
                System.out.println("naem");
                nameUserInputPart2=txtEnterName.getText();
                lblAlredyAddName.setVisible(false);

            }
        }

    }

    @FXML
    void buttonCheckemailOnACT(ActionEvent event) {

        if(txtEnteremail.getText().isEmpty()){
            AlertController.animationMesseagewrong("Error","Please Enter your email.");

        }else {
            String email = txtEnteremail.getText();
            String emailUserInput = new String();

            if(ValidateField.emailCheck(txtEnteremail.getText())){
                lblInvalidEmail.setVisible(false);
                try {
                    CreateNewAccount item = CreateNewAccountModel.search(email);
                    if (item != null) {
                        emailUserInput=item.getEmail();
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                    AlertController.animationMesseagewrong("Error","something went wrong!");
                }

            }else {
                lblInvalidEmail.setVisible(true);
            }

            if(emailUserInput.equals(txtEnteremail.getText())){
                lblAlredyAddEmail.setVisible(true);
            }else {
                lblAlredyAddEmail.setVisible(false);
                emailUserInputPart2=txtEnteremail.getText();
            }
        }
    }

    public void CreateOnAction(ActionEvent actionEvent) throws IOException {

        if(cmbAdminCashiar.getValue()!="Admin"&&cmbAdminCashiar.getValue()!="Cashier"&&txtEnterName.getText().isEmpty()&&txtEnteremail.getText().isEmpty()&&txtPassword.getText().isEmpty()&&txtConfirmPassword.getText().isEmpty()){
            AlertController.animationMesseagewrong("Error","Please fill the feald..");

        }else {

            if (txtEnterName.getText().isEmpty() && txtEnteremail.getText().isEmpty() && txtPassword.getText().isEmpty() && txtConfirmPassword.getText().isEmpty()) {
                    AlertController.animationMesseagewrong("Error","Please fill the feald..");
                } else {
                    if(txtEnterName.getText().isEmpty()){
                        AlertController.animationMesseagewrong("Error","Please Enter Name..");
                    }else {
                        if(txtEnteremail.getText().isEmpty()){
                            AlertController.animationMesseagewrong("Error","Please Enter Email..");
                        }else {
                            if(txtPassword.getText().isEmpty()){
                                AlertController.animationMesseagewrong("Error","Please Enter Password..");
                            }else {
                                if(txtConfirmPassword.getText().isEmpty()){
                                    AlertController.animationMesseagewrong("Error","Please Enter Confirm password..");
                                }else{
                                    if(cmbAdminCashiar.getValue()!="Admin"&&cmbAdminCashiar.getValue()!="Cashier"){
                                        AlertController.animationMesseagewrong("Error","Please Change Admin or Cashier..");
                                    }else {
                                        if(nameUserInputPart2.isEmpty()||emailUserInputPart2.isEmpty()){
                                            AlertController.animationMesseagewrong("Error","Please check your name and email. \n press the (CHECK) button!");

                                        }else {
                                            if(emailUserInputPart2.isEmpty()){
                                                AlertController.animationMesseagewrong("Error","Please check your email.");
                                            }else {
                                                if(nameUserInputPart2.isEmpty()){
                                                    AlertController.animationMesseagewrong("Error","Please check your name.");
                                                }else {
                                                    if(nameUserInputPart2.equals(txtEnterName.getText())&&emailUserInputPart2.equals(txtEnteremail.getText())){
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

//                                        txtConfirmPassword.setText("");
//
//                                        txtEnterName.setText("");
//                                        txtPassword.setText("");
//                                        txtConfirmPassword.setText("");
//                                        txtEnteremail.setText("");

                                                            }else {

                                                                String password = txtPassword.getText();
                                                                String email = txtEnteremail.getText();
                                                                String tittle = String.valueOf(cmbAdminCashiar.getValue());

                                                                UserCreateAcountSetAndGet cus = new UserCreateAcountSetAndGet(nameUserInputPart2, password, emailUserInputPart2, tittle);

                                                                try {
//            boolean isSaved = ItemModel.save(code, description, unitPrice, qtyOnHand);
                                                                    boolean isSaved = UserCreateAcountSetAndGetModel.save(cus);
                                                                    if (isSaved) {
                                                                        AlertController.animationMesseageCorect("CONFIRMATION","Account Created !");
                                                                        txtEnterName.setText("");
                                                                        txtEnteremail.setText("");
                                                                        txtPassword.setText("");
                                                                        txtConfirmPassword.setText("");
                                                                        cmbAdminCashiar.setValue(null);

                                                                    }
                                                                } catch (SQLException e) {
                                                                    System.out.println(e);

                                                                    AlertController.animationMesseagewrong("Error","Please check your name and email. \n press the CHECK button!");
                                                                }
                                                            }


                                                        } else {

                                                            AlertController.animationMesseagewrong("Error","Please Try your Confirm password");
                                                            txtConfirmPassword.setText("");

                                                        }
                                                    }else {
                                                        AlertController.animationMesseagewrong("Error","Please check your name and email. \n press the (CHECK) button!");

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
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

        lblAlredyAddEmail.setVisible(false);
        lblInvalidEmail.setVisible(false);
        lblAlredyAddName.setVisible(false);
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
