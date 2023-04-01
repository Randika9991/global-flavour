package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.global_flavour.dto.Suppliers;
import lk.ijse.global_flavour.dto.tm.EmployeeTM;
import lk.ijse.global_flavour.dto.tm.SuppliersTM;
import lk.ijse.global_flavour.model.EmployeeSetAndGetModel;
import lk.ijse.global_flavour.model.SuppliersModel;

public class SuppliersFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private TextField txtsearchSupplier;

    @FXML
    private JFXTextField txtsupId;

    @FXML
    private JFXTextField txtsupName;

    @FXML
    private JFXTextField txtsupAddress;

    @FXML
    private JFXTextField txtsupContact;

    @FXML
    private JFXTextField txtsupEmail;

    @FXML
    private TableView<SuppliersTM> mainCOMSupliar;

    @FXML
    private TableColumn<?, ?> COMSupId;

    @FXML
    private TableColumn<?, ?> COMSupName;

    @FXML
    private TableColumn<?, ?> COMSupAddress;

    @FXML
    private TableColumn<?, ?> COMSupContact;

    @FXML
    private TableColumn<?, ?> COMSupEmail;

    @FXML
    void buttonSaveOnACT(ActionEvent event) {
        String SupId = txtsupId.getText();
        String SupName = txtsupName.getText();
        String SupAddress = txtsupAddress.getText();
        String supContact = txtsupContact.getText();
        String supEmail = txtsupEmail.getText();

        Suppliers itemSup = new Suppliers(SupId, SupName, SupAddress,supEmail,supContact);

        try {
//            boolean isSaved = ItemModel.save(code, description, unitPrice, qtyOnHand);
            boolean isSaved = SuppliersModel.save(itemSup);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
                onActionGetAllSuppliers();
            }
        } catch (SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String SupId = txtsupId.getText();
        String SupName = txtsupName.getText();
        String SupAddress = txtsupAddress.getText();
        String supContact = txtsupContact.getText();
        String supEmail = txtsupEmail.getText();

        Suppliers itemSup = new Suppliers(SupId, SupName, SupAddress,supEmail,supContact);

        try {
            boolean isUpdated = SuppliersModel.update(itemSup);
            new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
            onActionGetAllSuppliers();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code = txtsupId.getText();
        try {
            boolean isDeleted = SuppliersModel.delete(code);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                onActionGetAllSuppliers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }




    @FXML
    void supIdOnAction(ActionEvent event) {
        String id = txtsupId.getText();

        try {
            Suppliers cust = SuppliersModel.search(id);
            if (cust != null) {
                txtsupId.setText(cust.getSupplierId());
                txtsupName.setText(cust.getSupplierName());
                txtsupAddress.setText(cust.getSupplierAddress());
                txtsupContact.setText(cust.getSupplierCotact());
                txtsupEmail.setText(cust.getSupplierEmail());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }


    }

    @FXML
    void supplierOnMouse(MouseEvent event) {
        TablePosition pos=mainCOMSupliar.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<SuppliersTM,?>> columns=mainCOMSupliar.getColumns();

        txtsupId.setText(columns.get(0).getCellData(row).toString());
        txtsupName.setText(columns.get(1).getCellData(row).toString());
        txtsupAddress.setText(columns.get(2).getCellData(row).toString());
        txtsupContact.setText(columns.get(3).getCellData(row).toString());
        txtsupEmail.setText(columns.get(4).getCellData(row).toString());

    }


    @FXML
    public void searchSupOnKey(KeyEvent keyEvent) throws SQLException {

        String searchValue=txtsearchSupplier.getText().trim();
        ObservableList<SuppliersTM>obList= SuppliersModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<SuppliersTM> filteredData = obList.filtered(new Predicate<SuppliersTM>(){
                @Override
                public boolean test(SuppliersTM suppliersTM) {
                    return String.valueOf(suppliersTM.getSupplierId()).toLowerCase().contains(searchValue.toLowerCase());        }
            });
            mainCOMSupliar.setItems(filteredData);} else {
            mainCOMSupliar.setItems(obList);
        }

    }

    public void supIdOnActionSerch(ActionEvent actionEvent) {
        String id = txtsearchSupplier.getText();

        try {
            Suppliers cust = SuppliersModel.search(id);
            if (cust != null) {
                txtsupId.setText(cust.getSupplierId());
                txtsupName.setText(cust.getSupplierName());
                txtsupAddress.setText(cust.getSupplierAddress());
                txtsupContact.setText(cust.getSupplierCotact());
                txtsupEmail.setText(cust.getSupplierEmail());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }

    }

    @FXML
    void initialize() {
        onActionGetAllSuppliers();
        setCellValuefactory();

    }

    void onActionGetAllSuppliers() {
        try {
            ObservableList<SuppliersTM> supList = SuppliersModel.getAll();
            mainCOMSupliar.setItems(supList);


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
        }

    }

    void setCellValuefactory(){
        COMSupId.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
        COMSupName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        COMSupAddress.setCellValueFactory(new PropertyValueFactory<>("SupplierAddress"));
        COMSupContact.setCellValueFactory(new PropertyValueFactory<>("SupplierEmail"));
        COMSupEmail.setCellValueFactory(new PropertyValueFactory<>("SupplierCotact"));

    }
}
