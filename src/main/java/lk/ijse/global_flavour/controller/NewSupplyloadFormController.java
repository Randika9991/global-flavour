package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.global_flavour.model.DeliveryModel;
import lk.ijse.global_flavour.model.SupplyModel;
import lk.ijse.global_flavour.util.AlertController;

import java.sql.SQLException;

public class NewSupplyloadFormController {

    @FXML
    private AnchorPane adminchangingPane;

    @FXML
    private JFXComboBox cmbitemcode;

    @FXML
    private TableView<?> colMainTable;

    @FXML
    private TableColumn<?, ?> colitemcode;

    @FXML
    private TableColumn<?, ?> colitemname;

    @FXML
    private TableColumn<?, ?> colcategory;

    @FXML
    private TableColumn<?, ?> colquantity;

    @FXML
    private TableColumn<?, ?> colaction;

    @FXML
    private JFXButton btnAddToLoad;

    @FXML
    private JFXComboBox cmbsuppid;

    @FXML
    private Label lblchangingitmname;

    @FXML
    private Label lblOrderId111;

    @FXML
    private Label lblchangingsuppname;

    @FXML
    private Label lblchangingcategory;

    @FXML
    private Label lblchangingqtyonhands;

    @FXML
    private Label lblloadid;

    @FXML
    private Label lblsupplydate;

    @FXML
    private Label lblsupplytime;

    @FXML
    private JFXTextField supplyqty;

    @FXML
    private JFXTextField supplyqty1;

    @FXML
    private JFXButton btnAddToLoad1;



    @FXML
    void btnaddcartOnAction(ActionEvent event) {

    }

    @FXML
    void btnplaceorderOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSuppIdOnAction(ActionEvent event) {

    }

    @FXML
    void cmbitemcodeOnAction(ActionEvent event) {

    }

    @FXML
    void itemOnMouse(MouseEvent event) {

    }

    @FXML
    void initialize() {
        onActionGetAllSupplierId();
        onActionGetAllItemCode();

    }
    void onActionGetAllSupplierId() {

        try {
            ObservableList<String> EmpList = SupplyModel.getAll();

            cmbsuppid.getItems().addAll(EmpList);

        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }
    }
    void onActionGetAllItemCode() {

        try {
            ObservableList<String> EmpList = SupplyModel.getAllItemCode();

            cmbitemcode.getItems().addAll(EmpList);

        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }
    }
}
