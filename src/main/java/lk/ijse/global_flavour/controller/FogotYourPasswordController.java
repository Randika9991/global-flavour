package lk.ijse.global_flavour.controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.global_flavour.dto.FogotYourPassword;
import lk.ijse.global_flavour.dto.LoginSetAndGet;
import lk.ijse.global_flavour.model.FogotYourPasswordModel;
import lk.ijse.global_flavour.model.LoginSetAndGetModel;
import lk.ijse.global_flavour.util.AlertController;

import javax.mail.MessagingException;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class FogotYourPasswordController {

    public static String emailShireChangePasswordController="";
    public static String adminShireChangePasswordController;
    public static String nameShireChangePasswordController;

    public static String getEmailShireChangePasswordController2() {
        return emailShireChangePasswordController;
    }

    public static String getAdminShireChangePasswordController2() {
        return adminShireChangePasswordController;
    }

    public static String getNameShireChangePasswordController2() {
        return nameShireChangePasswordController;
    }

    LoginPageController loginPageController=new LoginPageController();

    @FXML
    public TextField txtEnteremail;

    @FXML
    public Hyperlink txtChangePassword;

    @FXML
    public JFXButton txtadd;

    @FXML
    private TextField txtEnterOTP;

    @FXML
    private AnchorPane fogotPassword;

    @FXML
    private JFXButton txtSubmit;

    Random rand = new Random();
    int randomnum ;

    String Emailhelp2;

    @FXML
    void submitOnAction(ActionEvent event) {
        randomnum = rand.nextInt(9000);
        randomnum +=1000;

//      "kumarasirirandika0706@gmail.com"

        String emailinput = txtEnteremail.getText();
        String adminCashiInput = new String();
        String youserInput=new String();


        if(loginPageController.getEmail().isEmpty()){
            String emailUserInput = new String();
            try {
                FogotYourPassword logSetGet = FogotYourPasswordModel.search(emailinput);
                if (logSetGet != null) {

                    emailUserInput=logSetGet.getEmail();
                    adminCashiInput=logSetGet.getJobtitel();
                    youserInput=logSetGet.getUsrname();



                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "something happened!").show();
            }
            if(emailUserInput.equals(txtEnteremail.getText())){
                emailShireChangePasswordController=emailinput;
                adminShireChangePasswordController=adminCashiInput;
                nameShireChangePasswordController=youserInput;

                try {
                    EmailController.sendEmail(txtEnteremail.getText(), "Test Email", randomnum+" Your OTP code here");
                    System.out.println("Email sent successfully.");
                    AlertController.notificationBar("SPICY FLAVOUR","Check Your Email OTP Send Success !");

                    txtadd.setVisible(true);

                } catch (MessagingException | AWTException e) {
                    e.printStackTrace();
                }
            }else {
                AlertController.animationMesseagewrong("Error","Can't find Email!");
            }

        }else {
            if(txtEnteremail.getText().isEmpty()){
                AlertController.animationMesseagewrong("Error","Please Enter Email!");
            }else {
                if(loginPageController.getEmail().equals(txtEnteremail.getText())){
                    try {
                        EmailController.sendEmail(loginPageController.getEmail(), "Test Email", randomnum+" Your OTP code here");
                        System.out.println("Email sent successfully.");
                        AlertController.notificationBar("SPICY FLAVOUR","Check Your Email OTP Send Success !");

                        txtadd.setVisible(true);

                    } catch (MessagingException | AWTException e) {
                        e.printStackTrace();
                    }
                }else {
                    AlertController.animationMesseagewrong("Error","Please Enter Correct Email!");
                }
            }

        }

    }

    @FXML
    public void addOnAction(ActionEvent actionEvent) {

        int addotp= Integer.parseInt(txtEnterOTP.getText());

        if (randomnum==addotp) {
            txtChangePassword.setVisible(true);
        }else {
            AlertController.animationMesseagewrong("Error","Wrong OTP Please Try again!");

        }

    }

    @FXML
    public void buttonOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        Parent root = null;
        stage.setTitle("SPICY FLAVOUR");
        stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/loginpage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        fogotPassword.getScene().getWindow().hide();

    }

    @FXML
    public void ChangePasswordOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        stage.setTitle("SPICY FLAVOUR");
        stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/changepassword.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        fogotPassword.getScene().getWindow().hide();
    }

    @FXML
    void initialize() throws SQLException {
        assert fogotPassword != null : "fx:id=\"fogotPassword\" was not injected: check your FXML file 'fogotpassworld.fxml'.";
        assert txtSubmit != null : "fx:id=\"txtSubmit\" was not injected: check your FXML file 'fogotpassworld.fxml'.";
        assert txtadd != null : "fx:id=\"txtadd\" was not injected: check your FXML file 'fogotpassworld.fxml'.";
        txtadd.setVisible(false);
        txtChangePassword.setVisible(false);
        System.out.println(loginPageController.getEmail());


    }
}









