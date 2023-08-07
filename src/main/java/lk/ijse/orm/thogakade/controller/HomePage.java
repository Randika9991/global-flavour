package lk.ijse.orm.thogakade.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomePage {

    @FXML
    private AnchorPane cashieranchorpane;

    @FXML
    private AnchorPane adminAncPane;

    @FXML
    void ItemOnAction(ActionEvent event) {

    }

    @FXML
    void custOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.orm.view/customerForm.fxml"));
        adminAncPane.getChildren().clear();
        adminAncPane.getChildren().add(load);
        //ButtonColourController.btncolor(HomeBtn,adminAncPane);

    }

    @FXML
    void orderOnAction(ActionEvent event) {

    }

}
