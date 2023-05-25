import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.eclipse.jdt.internal.compiler.parser.Scanner;

import java.net.URL;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch();

    }
    @Override
    public void start(Stage stage) throws Exception {
     URL resource = Launcher.class.getResource("lk.ijse.global_flavour.view/loadingpage.fxml");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));
        stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-chilli-100.png"));
        stage.setTitle("SPICY FLAVOUR");
        stage.centerOnScreen();
        stage.show();
    }


}