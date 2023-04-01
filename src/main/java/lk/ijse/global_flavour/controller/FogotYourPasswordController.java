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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Random;

public class FogotYourPasswordController {

    public TextField txtEnteremail;
    public Hyperlink txtChangePassword;
    public JFXButton txtadd;

    public void dfhfhfhfh(ActionEvent actionEvent) {

    }

    @FXML
    private TextField txtEnterOTP;

    @FXML
    private AnchorPane fogotPassword;

    @FXML
    private JFXButton txtSubmit;

    public void buttonOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/loginpage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        fogotPassword.getScene().getWindow().hide();

    }

    Random rand = new Random();
    String enteremail ;
    int randomnum ;

    @FXML
    void submitOnAction(ActionEvent event) {

        randomnum = rand.nextInt(9000);
        randomnum +=1000;
        enteremail = txtEnteremail.getText();

//        "kumarasirirandika0706@gmail.com"

        try {
            EmailController.sendEmail(enteremail, "Test Email", randomnum+" Your OTP code here");
            System.out.println("Email sent successfully.");

            txtadd.setVisible(true);


        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    public void addOnAction(ActionEvent actionEvent) {
        int addotp= Integer.parseInt(txtEnterOTP.getText());

             if (randomnum==addotp) {
                 txtChangePassword.setVisible(true);
             }else {
             new Alert(Alert.AlertType.CONFIRMATION,"Wrong OTP").show();
        }

        }


    public void ChangePasswordOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/changepassword.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        fogotPassword.getScene().getWindow().hide();


    }

    @FXML
    void initialize() {
        assert fogotPassword != null : "fx:id=\"fogotPassword\" was not injected: check your FXML file 'fogotpassworld.fxml'.";
        assert txtSubmit != null : "fx:id=\"txtSubmit\" was not injected: check your FXML file 'fogotpassworld.fxml'.";
        assert txtadd != null : "fx:id=\"txtadd\" was not injected: check your FXML file 'fogotpassworld.fxml'.";
        txtadd.setVisible(false);
        txtChangePassword.setVisible(false);

    }


}









