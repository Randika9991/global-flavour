package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.global_flavour.dto.tm.CashierCustomerTM;
import lk.ijse.global_flavour.dto.tm.ItemTM;
import lk.ijse.global_flavour.model.AdminSalaryModel;
import lk.ijse.global_flavour.model.CashierCustomerModel;
import lk.ijse.global_flavour.dto.CashierCustomer;
import lk.ijse.global_flavour.model.ItemModel;

import java.sql.SQLException;
import java.util.function.Predicate;

public class CashiercustomerFormController {

    @FXML
    private JFXTextField txtCusId;

    @FXML
    private JFXTextField txtCusName;

    @FXML
    private JFXTextField txtCusContact;

    @FXML
    private JFXTextField txtCusAddress;

    @FXML
    private JFXTextField txtCusUserName;

    @FXML
    private TableView<CashierCustomerTM> mainCOMCustomer;

    @FXML
    private TableColumn<?, ?> COMCusId;

    @FXML
    private TableColumn<?, ?> COMuserName;

    @FXML
    private TableColumn<?, ?> COMCustomName;

    @FXML
    private TableColumn<?, ?> COMCustContact;

    @FXML
    private TableColumn<?, ?> COMCustAddress;

    @FXML
    private TableColumn<?, ?> COMCusEmail;

    @FXML
    private TextField txtsearchCustom;

    @FXML
    private JFXTextField txtCusEmail1;

    @FXML
    void buttonSaveOnACT(ActionEvent event) {
        String CusId = txtCusId.getText();
        String UserName = txtCusUserName.getText();
        String CusName = txtCusName.getText();
        String CusContact = txtCusContact.getText();
        String CusAddress = txtCusAddress.getText();
        String CusEmail1 = txtCusEmail1.getText();


        CashierCustomer allCustom = new CashierCustomer(CusId, UserName, CusName,CusContact,CusAddress,CusEmail1);

        try {
//            boolean isSaved = ItemModel.save(code, description, unitPrice, qtyOnHand);
            boolean isSaved = CashierCustomerModel.save(allCustom);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
               // onActionGetAllItem();
            }
        } catch (SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String CusId = txtCusId.getText();
        String UserName = txtCusUserName.getText();
        String CusName = txtCusName.getText();
        String CusContact = txtCusContact.getText();
        String CusAddress = txtCusAddress.getText();
        String CusEmail1 = txtCusEmail1.getText();


        CashierCustomer allCustom = new CashierCustomer(CusId, UserName, CusName,CusContact,CusAddress,CusEmail1);

        try {
            boolean isUpdated = CashierCustomerModel.update(allCustom);
            new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
            //onActionGetAllItem();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code = txtCusId.getText();
        try {
            boolean isDeleted = CashierCustomerModel.delete(code);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                //onActionGetAllItem();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }

    @FXML
    void CusIdOnActionSerch(ActionEvent event) {
        String id = txtsearchCustom.getText();

        try {
            CashierCustomer cust = CashierCustomerModel.search(id);
            if (cust != null) {
                txtCusId.setText(cust.getCustomerId());
                txtCusUserName.setText(cust.getUserName());
                txtCusName.setText(cust.getCustomerName());
                txtCusContact.setText(cust.getContactNo());
                txtCusAddress.setText(cust.getAddress());
                txtCusEmail1.setText(cust.getEmail());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }

    }

    @FXML
    void cusIdOnAction(ActionEvent event) {

    }

    @FXML
    void searchCusOnKey(KeyEvent event) throws SQLException {
        String searchValue=txtsearchCustom.getText().trim();
        ObservableList<CashierCustomerTM>obList= CashierCustomerModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<CashierCustomerTM> filteredData = obList.filtered(new Predicate<CashierCustomerTM>(){
                @Override
                public boolean test(CashierCustomerTM cashierCustomerTM) {
                    return String.valueOf(cashierCustomerTM.getCustomerId()).toLowerCase().contains(searchValue.toLowerCase());        }
            });
            mainCOMCustomer.setItems(filteredData);} else {
            mainCOMCustomer.setItems(obList);
        }


    }

    @FXML
    void supplierOnMouse(MouseEvent event) {
        TablePosition pos=mainCOMCustomer.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<CashierCustomerTM,?>> columns=mainCOMCustomer.getColumns();

        txtCusId.setText(columns.get(0).getCellData(row).toString());
        txtCusUserName.setText(columns.get(1).getCellData(row).toString());
        txtCusName.setText(columns.get(2).getCellData(row).toString());
        txtCusContact.setText(columns.get(3).getCellData(row).toString());
        txtCusAddress.setText(columns.get(4).getCellData(row).toString());
        txtCusEmail1.setText(columns.get(5).getCellData(row).toString());

    }
    @FXML
    void initialize() {
        onActionGetAllCustom();
        setCellValuefactory();


    }

    void onActionGetAllCustom() {
        try {
            ObservableList<CashierCustomerTM> supList = CashierCustomerModel.getAll();
            mainCOMCustomer.setItems(supList);


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
        }

    }

    void setCellValuefactory(){
        COMCusId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        COMuserName.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        COMCustomName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        COMCustContact.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        COMCustAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        COMCusEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }



}
