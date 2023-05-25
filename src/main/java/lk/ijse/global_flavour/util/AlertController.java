//all added

package lk.ijse.global_flavour.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.paint.Color;



public class AlertController {
    public static void notificationBar(String title,String massage) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        java.awt.Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Notification Example");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Click me to see the message");
        tray.add(trayIcon);
        trayIcon.displayMessage(title, massage, TrayIcon.MessageType.INFO);
    }

    public static boolean okconfirmmessage(String msg){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.getDialogPane().setBackground(new Background(new BackgroundFill(null, null, null)));

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
//            alert.getDialogPane().setMinSize(300, 150); // Set the minimum size of the alert dialog pane
//            alert.getDialogPane().setMaxSize(300, 150); // Set the maximum size of the alert dialog pane
        //alert.getDialogPane().setStyle("-fx-background-color: #F8D7DA;"); // Set the background color of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: linear-gradient(to top right  ,#043b38,white);");
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane


        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-help-100.png"));
        ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.orElse(cancelButton) == okButton){
            return true;
        }
        return false;
    }
    public static void animationMesseageCorect(String Title, String Message) {

        Image image = new Image("lk.ijse.global_flavour.assets/icons8-ok.gif");

        // Create an alert with a title, message, and icon
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(Message);
        alert.getDialogPane().setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        alert.getDialogPane().setStyle("-fx-background-color: linear-gradient(to top right  ,#bcbcbc,#bcbcbc,#404040);");
        alert.getDialogPane().lookup(".header-panel .label").setStyle("-fx-text-fill: red;");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-ok.gif"));
        alert.setGraphic(new ImageView(image));
        alert.showAndWait();

    }

    public static void animationMesseagewrong(String Title, String Message) {

        Image image = new Image("lk.ijse.global_flavour.assets/icons8-brake-warning.gif");

        // Create an alert with a title, message, and icon
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(Message);
        alert.getDialogPane().lookup(".header-panel .label").setStyle("-fx-text-fill: red;");
        alert.getDialogPane().setStyle("-fx-background-color: linear-gradient(to top right  ,#bcbcbc,#bcbcbc,#404040);");
        alert.getDialogPane().setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        alert.setGraphic(new ImageView(image));
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-high-importance-96.png"));

        alert.showAndWait();

    }
}
