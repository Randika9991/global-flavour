//all added
package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.global_flavour.dto.CashierVehicle;
import lk.ijse.global_flavour.dto.tm.CashierVehicleTM;
import lk.ijse.global_flavour.dto.tm.ItemTM;
import lk.ijse.global_flavour.model.CashierVehicleModel;
import lk.ijse.global_flavour.model.ItemModel;
import lk.ijse.global_flavour.util.AlertController;
import lk.ijse.global_flavour.util.ValidateField;

import java.sql.SQLException;
import java.util.function.Predicate;

public class CashierVehicleFormController {

    @FXML
    private AnchorPane adminAncPane;

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
    private Label lblInvalidVehical;

    @FXML
    void buttonSaveOnACT(ActionEvent event) {
        if(txtVehiId.getText().isEmpty()||txtVehiNo.getText().isEmpty()||txtVehitype.getText().isEmpty()){
            AlertController.animationMesseagewrong("Error","Vehicle details not saved. \nPlease make sure to fill the request fields.");

        }else {

            if(ValidateField.VehicalIdCheck(txtVehiId.getText())){
                lblInvalidVehical.setVisible(false);
                String vehiId = txtVehiId.getText();
                String vehiNo = txtVehiNo.getText();
                String vehitype = txtVehitype.getText();

                CashierVehicle allvehi = new CashierVehicle(vehiId, vehiNo,vehitype);

                try {
                    boolean isSaved = CashierVehicleModel.save(allvehi);
                    if (isSaved) {
                        AlertController.animationMesseageCorect("CONFIRMATION","Vehicle Save Success!");
                        onActionGetAllItem();
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    AlertController.animationMesseagewrong("Error","something went wrong!");
                }

            }else {
                lblInvalidVehical.setVisible(true);
            }

        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        if(txtVehiId.getText().isEmpty()||txtVehiNo.getText().isEmpty()||txtVehitype.getText().isEmpty()){
            AlertController.animationMesseagewrong("Error","Vehicle details not updated. \nPlease make sure to fill the request fields.");

        }else {
            String vehiId = txtVehiId.getText();
            String vehiNo = txtVehiNo.getText();
            String vehitype = txtVehitype.getText();

            CashierVehicle allvehi = new CashierVehicle(vehiId, vehiNo,vehitype);

            try {
                boolean isUpdated = CashierVehicleModel.update(allvehi);
                if(isUpdated){
                    AlertController.animationMesseageCorect("CONFIRMATION","Vehicle updated!");
                    onActionGetAllItem();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                AlertController.animationMesseagewrong("Error","something went wrong!");
            }

        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        if(txtVehiId.getText().isEmpty()){

        }else {
            boolean ok = AlertController.okconfirmmessage("Are you Sure. Do you wont Delete item");

            if(ok){
                String code = txtVehiId.getText();
                try {
                    boolean isDeleted = CashierVehicleModel.delete(code);
                    if (isDeleted) {
                        AlertController.animationMesseageCorect("CONFIRMATION","Delete Success!");
                        onActionGetAllItem();
                    }
                } catch (SQLException e) {
                    AlertController.animationMesseagewrong("Error","something went wrong!");
                }
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
            AlertController.animationMesseagewrong("Error","something went wrong!");
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
        lblInvalidVehical.setVisible(false);

    }

    void onActionGetAllItem() {
        try {
            ObservableList<CashierVehicleTM> supList = CashierVehicleModel.getAll();
            mainCOMVehical.setItems(supList);


        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }

    }

    void setCellValuefactory(){
        COMVehiId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        COMVehiNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        COMVehiType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));

    }

    public void lblClearAllOnAction(ActionEvent actionEvent) {
        txtVehiId.setText("");
        txtVehiNo.setText("");
        txtVehitype.setText("");
    }
}
