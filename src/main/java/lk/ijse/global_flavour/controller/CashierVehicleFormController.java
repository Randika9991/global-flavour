package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.global_flavour.dto.CashierVehicle;
import lk.ijse.global_flavour.dto.tm.CashierVehicleTM;
import lk.ijse.global_flavour.dto.tm.ItemTM;
import lk.ijse.global_flavour.model.CashierVehicleModel;
import lk.ijse.global_flavour.model.ItemModel;
import lk.ijse.global_flavour.util.AlertController;

import java.sql.SQLException;
import java.util.function.Predicate;

public class CashierVehicleFormController {

    @FXML
    private JFXTextField txtVehiId;

    @FXML
    private JFXTextField txtVehiNo;

    @FXML
    private JFXTextField txtVehitype;

    @FXML
    private TableView<CashierVehicleTM> mainCOMVehical;

    @FXML
    private TableColumn<?, ?> COMVehiId;

    @FXML
    private TableColumn<?, ?> COMVehiNo;

    @FXML
    private TableColumn<?, ?> COMVehiType;

    @FXML
    private TextField txtsearchVehical;

    @FXML
    void buttonSaveOnACT(ActionEvent event) {
        String vehiId = txtVehiId.getText();
        String vehiNo = txtVehiNo.getText();
        String vehitype = txtVehitype.getText();

        CashierVehicle allvehi = new CashierVehicle(vehiId, vehiNo,vehitype);

        try {
            boolean isSaved = CashierVehicleModel.save(allvehi);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
                onActionGetAllItem();
            }
        } catch (SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        boolean ok = AlertController.okconfirmmessage("Are you Sure. Do you wont Update item");

        if(ok){
            String vehiId = txtVehiId.getText();
            String vehiNo = txtVehiNo.getText();
            String vehitype = txtVehitype.getText();

            CashierVehicle allvehi = new CashierVehicle(vehiId, vehiNo,vehitype);

            try {
                boolean isUpdated = CashierVehicleModel.update(allvehi);
                new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
                onActionGetAllItem();
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
            }
        }


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        boolean ok = AlertController.okconfirmmessage("Are you Sure. Do you wont Delete item");

        if(ok){
            String code = txtVehiId.getText();
            try {
                boolean isDeleted = CashierVehicleModel.delete(code);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                    onActionGetAllItem();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
            }
        }

    }

    @FXML
    void CusIdOnActionSerch(ActionEvent event) {
        String id = txtsearchVehical.getText();

        try {
            CashierVehicle cust = CashierVehicleModel.search(id);
            if (cust != null) {
                txtVehiId.setText(cust.getVehicleId());
                txtVehiNo.setText(cust.getVehicleNo());
                txtVehitype.setText(cust.getVehicleType());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }

    }

    @FXML
    void searchCusOnKey(KeyEvent event) throws SQLException {
        String searchValue=txtsearchVehical.getText().trim();
        ObservableList<CashierVehicleTM>obList= CashierVehicleModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<CashierVehicleTM> filteredData = obList.filtered(new Predicate<CashierVehicleTM>(){
                @Override
                public boolean test(CashierVehicleTM itemTM) {
                    return String.valueOf(itemTM.getVehicleId()).toLowerCase().contains(searchValue.toLowerCase());        }
            });
            mainCOMVehical.setItems(filteredData);} else {
            mainCOMVehical.setItems(obList);
        }


    }

    @FXML
    void supplierOnMouse(MouseEvent event) {
        TablePosition pos=mainCOMVehical.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<CashierVehicleTM,?>> columns=mainCOMVehical.getColumns();

        txtVehiId.setText(columns.get(0).getCellData(row).toString());
        txtVehiNo.setText(columns.get(1).getCellData(row).toString());
        txtVehitype.setText(columns.get(2).getCellData(row).toString());
    }

    @FXML
    void vehiIdOnAction(ActionEvent event) {

    }
    @FXML
    void initialize() {
        onActionGetAllItem();
        setCellValuefactory();

    }

    void onActionGetAllItem() {
        try {
            ObservableList<CashierVehicleTM> supList = CashierVehicleModel.getAll();
            mainCOMVehical.setItems(supList);


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
        }

    }

    void setCellValuefactory(){
        COMVehiId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        COMVehiNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        COMVehiType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));

    }

}
