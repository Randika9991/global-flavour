package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.global_flavour.dto.DeliverForm;
import lk.ijse.global_flavour.dto.Delivery;
import lk.ijse.global_flavour.dto.EmployeeSetAndGet;
import lk.ijse.global_flavour.dto.tm.CashierVehicleTM;
import lk.ijse.global_flavour.dto.tm.DeliverFormTM;
import lk.ijse.global_flavour.dto.tm.EmployeeTM;
import lk.ijse.global_flavour.model.CashierVehicleModel;
import lk.ijse.global_flavour.model.DeliveryModel;
import lk.ijse.global_flavour.model.EmployeeSetAndGetModel;
import lk.ijse.global_flavour.util.AlertController;

import java.sql.SQLException;
import java.time.LocalDate;

public class DeliverFormController {

    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblDeliverId;

    @FXML
    private DatePicker txtEmpDOBBox;

    @FXML
    private DatePicker txtDeliverdDate;

    @FXML
    private TableView<DeliverFormTM> tablOrder;

    @FXML
    private TableColumn<?, ?> CLMOrderID;

    @FXML
    private TableColumn<?, ?> CLMDeliveryID;

    @FXML
    private TableColumn<?, ?> CLMVehicaID;

    @FXML
    private TableColumn<?, ?> CLMDeliveryStatus;

    @FXML
    private TableColumn<?, ?> CLMDueDate;

    @FXML
    private TableColumn<?, ?> CLMDeliveredDate;

    @FXML
    private TableColumn<?, ?> CLMLocation;

    @FXML
    private TableColumn<?, ?> CLMEmployee;

    @FXML
    private JFXTextField txtLocation;

    @FXML
    private JFXComboBox cmbEmpId;

    @FXML
    private JFXComboBox cmbDeliveryStatus;

    @FXML
    private JFXComboBox cmbVehicalId;


    @FXML
    void OrderMarkOnMouse(MouseEvent event) {
        TablePosition pos=tablOrder.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<DeliverFormTM,?>> columns=tablOrder.getColumns();

        lblOrderId.setText(columns.get(0).getCellData(row).toString());
        lblDeliverId.setText(columns.get(1).getCellData(row).toString());
        cmbVehicalId.setValue(columns.get(2).getCellData(row).toString());

        cmbDeliveryStatus.setValue(columns.get(3).getCellData(row).toString());
        txtEmpDOBBox.setValue(LocalDate.parse(columns.get(4).getCellData(row).toString()));
        txtDeliverdDate.setValue(LocalDate.parse(columns.get(5).getCellData(row).toString()));
        txtLocation.setText(columns.get(6).getCellData(row).toString());
        cmbEmpId.setValue(columns.get(7).getCellData(row).toString());
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String orderId = lblOrderId.getText();
        String deliverId = lblDeliverId.getText();
        String VehicalId = String.valueOf(cmbVehicalId.getValue());
        Boolean deliverStatus = Boolean.valueOf(String.valueOf(cmbDeliveryStatus.getValue()));

        LocalDate dueDate = txtEmpDOBBox.getValue();
        LocalDate deliverDate = txtDeliverdDate.getValue();
        String location = txtLocation.getText();
        String employee = String.valueOf(cmbEmpId.getValue());


        DeliverForm allDeliver = new DeliverForm(deliverId, employee, orderId, VehicalId, location, deliverDate, dueDate, deliverStatus);
        try {

            boolean isUpdated = DeliveryModel.change(allDeliver);
            AlertController.animationMesseageCorect("CONFIRMATION","Employee updated!");
            //onActionGetAllEmployee();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }
    }

    @FXML
    void cmbDeliveryStatusOnAction(ActionEvent event) {

    }

    @FXML
    void cmbEmpIdOnAction(ActionEvent event) {

    }

    void onActionGetAllEmployeeaddToDelivery() {

        try {
            ObservableList<String> EmpList = DeliveryModel.getAll();

            cmbEmpId.getItems().addAll(EmpList);

        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }
    }
    void onActionGetAllDeliveryStatus() {

        try {
            ObservableList<String> EmpList = DeliveryModel.getAllDelivery();

            cmbDeliveryStatus.getItems().addAll(EmpList);

        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }
    }

    @FXML
    void employeeMarkOnMouse(MouseEvent event) {

    }

    void onActionGetAllVehicalIdaddToDelivery() {

        try {
            ObservableList<String> EmpList = DeliveryModel.getAllVehicalId();

            cmbVehicalId.getItems().addAll(EmpList);

        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }
    }

    @FXML
    void initialize() {
        onActionGetAllEmployeeaddToDelivery();
        onActionGetAllDeliveryStatus();
        onActionGetAllDelivery();
        setCellValuefactory();
        onActionGetAllVehicalIdaddToDelivery();
    }

    void onActionGetAllDelivery() {
        try {
            ObservableList<DeliverFormTM> supList = DeliveryModel.getAllDeliveryFromController();
            tablOrder.setItems(supList);

        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }
    }

    void setCellValuefactory(){
        CLMOrderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        CLMDeliveryID.setCellValueFactory(new PropertyValueFactory<>("deliverId"));
        //
        CLMVehicaID.setCellValueFactory(new PropertyValueFactory<>("vehicalId"));
        CLMDeliveryStatus.setCellValueFactory(new PropertyValueFactory<>("deliverStatus"));
        CLMDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        CLMDeliveredDate.setCellValueFactory(new PropertyValueFactory<>("deliverDate"));
        CLMLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        CLMEmployee.setCellValueFactory(new PropertyValueFactory<>("empId"));

    }

    public void lblClearAllOnAction(ActionEvent actionEvent) {

    }
}
