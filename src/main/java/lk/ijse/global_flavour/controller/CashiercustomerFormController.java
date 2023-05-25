//all added
package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.global_flavour.dto.EmployeeSetAndGet;
import lk.ijse.global_flavour.dto.tm.CashierCustomerTM;
import lk.ijse.global_flavour.dto.tm.ItemTM;
import lk.ijse.global_flavour.model.AdminSalaryModel;
import lk.ijse.global_flavour.model.CashierCustomerModel;
import lk.ijse.global_flavour.dto.CashierCustomer;
import lk.ijse.global_flavour.model.EmployeeSetAndGetModel;
import lk.ijse.global_flavour.model.ItemModel;
import lk.ijse.global_flavour.util.AlertController;
import lk.ijse.global_flavour.util.ValidateField;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;

public class CashiercustomerFormController {

    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private JFXTextField txtCusId;

    @FXML
    private JFXTextField txtCusName;

    @FXML
    private JFXTextField txtCusContact;

    @FXML
    private JFXTextField txtCusAddress;

    @FXML
    private TableView<CashierCustomerTM> mainCOMCustomer;

    @FXML
    private TableColumn<?, ?> COMCusId;

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
    private Label lblInvalidContactNo;

    @FXML
    private Label lblInvalidEmail;

    @FXML
    private Label lblInvalidCustomID;

    @FXML
    private Label lblAlredyAdded;

    @FXML
    private Label lblAlredyAddedContact;


    @FXML
    void buttonSaveOnACT(ActionEvent event) {
        if(txtCusId.getText().isEmpty() || txtCusName.getText().isEmpty() || txtCusAddress.getText().isEmpty() ||txtCusContact.getText().isEmpty()||txtCusEmail1.getText().isEmpty()){
            AlertController.animationMesseagewrong("Error","Customer details not saved. \nPlease make sure to fill the request fields.");
        }else {

            if(ValidateField.CustomerIdCheck(txtCusId.getText()) ||ValidateField.contactCheck(txtCusContact.getText()) ||ValidateField.emailCheck(txtCusEmail1.getText())){
                if(ValidateField.emailCheck(txtCusEmail1.getText())){
                    if(ValidateField.contactCheck(txtCusContact.getText())){
                        if(ValidateField.CustomerIdCheck(txtCusId.getText())){
                            if(preContact.equals(txtCusContact.getText())){
                                lblAlredyAddedContact.setVisible(true);

                            }else {
                                lblInvalidCustomID.setVisible(false);
                                String CusId = txtCusId.getText();
                                String CusName = txtCusName.getText();
                                String CusContact = txtCusContact.getText();
                                String CusAddress = txtCusAddress.getText();
                                String CusEmail1 = txtCusEmail1.getText();


                                CashierCustomer allCustom = new CashierCustomer(CusId, CusName,CusContact,CusAddress,CusEmail1);

                                try {
//
                                    boolean isSaved = CashierCustomerModel.save(allCustom);
                                    if (isSaved) {
                                        AlertController.animationMesseageCorect("CONFIRMATION","Customer Save Success!");
                                        onActionGetAllCustom();
                                    }
                                } catch (SQLException e) {
                                    System.out.println(e);
                                    AlertController.animationMesseagewrong("Error","something went wrong!");
                                }
                            }

                        }else {
                            lblInvalidCustomID.setVisible(true);
                        }
                    }else {
                        lblInvalidContactNo.setVisible(true);
                    }

                }else {
                    lblInvalidEmail.setVisible(true);
                }
            }else {
                lblInvalidContactNo.setVisible(true);
                lblInvalidEmail.setVisible(true);
                lblInvalidCustomID.setVisible(true);

            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if(txtCusId.getText().isEmpty() || txtCusName.getText().isEmpty() || txtCusAddress.getText().isEmpty()){
            AlertController.animationMesseagewrong("Error","Customer details not saved. \nPlease make sure to fill the request fields.");
        }else {

            if(ValidateField.contactCheck(txtCusContact.getText()) ||ValidateField.emailCheck(txtCusEmail1.getText())){
                if(ValidateField.emailCheck(txtCusEmail1.getText())){
                    if(ValidateField.contactCheck(txtCusContact.getText())){
                        String CusId = txtCusId.getText();
                        String CusName = txtCusName.getText();
                        String CusContact = txtCusContact.getText();
                        String CusAddress = txtCusAddress.getText();
                        String CusEmail1 = txtCusEmail1.getText();


                        CashierCustomer allCustom = new CashierCustomer(CusId, CusName,CusContact,CusAddress,CusEmail1);

                        try {
                            boolean isUpdated = CashierCustomerModel.update(allCustom);
                            AlertController.animationMesseageCorect("CONFIRMATION","Customer updated!");
                            onActionGetAllCustom();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            AlertController.animationMesseagewrong("Error","something went wrong!");
                        }
                    }else {
                        lblInvalidContactNo.setVisible(true);
                    }

                }else {
                    lblInvalidEmail.setVisible(true);
                }
            }else {
                lblInvalidContactNo.setVisible(true);
                lblInvalidEmail.setVisible(true);

            }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        if(txtCusId.getText().isEmpty()){

        }else{
            boolean ok = AlertController.okconfirmmessage("Are you Sure. Do you wont Delete item");

            if(ok){
                String code = txtCusId.getText();
                try {
                    boolean isDeleted = CashierCustomerModel.delete(code);
                    if (isDeleted) {
                        AlertController.animationMesseageCorect("CONFIRMATION","Delete Success!");
                        onActionGetAllCustom();
                    }
                } catch (SQLException e) {
                    AlertController.animationMesseagewrong("Error","something went wrong!");
                }

            }
        }
    }

    @FXML
    void CusIdOnActionSerch(ActionEvent event) {
        String id = txtsearchCustom.getText();

        try {
            CashierCustomer cust = CashierCustomerModel.search(id);
            if (cust != null) {
                txtCusId.setText(cust.getCustomerId());
                txtCusName.setText(cust.getCustomerName());
                txtCusContact.setText(cust.getContactNo());
                txtCusAddress.setText(cust.getAddress());
                txtCusEmail1.setText(cust.getEmail());
            }
        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
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
        txtCusName.setText(columns.get(1).getCellData(row).toString());
        txtCusContact.setText(columns.get(2).getCellData(row).toString());
        txtCusAddress.setText(columns.get(3).getCellData(row).toString());
        txtCusEmail1.setText(columns.get(4).getCellData(row).toString());

    }

    @FXML
    void txtContactNumberOnMouseClick(MouseEvent event) {
        lblInvalidContactNo.setVisible(false);
    }

    @FXML
    void txtemailOnMouseClick(MouseEvent event) {
        lblInvalidEmail.setVisible(false);

    }

    String preContact;
    private void loadCustomerContact() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = CashierCustomerModel.loadContact();

            for (String id : ids) {
                obList.add(id);
            }
            //cmbCustomerId.setItems(obList);
            preContact= String.valueOf(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }
    }

    String preemail;
    private void loadCustomeremail() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = CashierCustomerModel.loademail();

            for (String id : ids) {
                obList.add(id);
            }
            //cmbCustomerId.setItems(obList);
            preemail= String.valueOf(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }
    }



    @FXML
    void initialize() {
        loadCustomeremail();
        loadCustomerContact();
        onActionGetAllCustom();
        setCellValuefactory();
        lblInvalidContactNo.setVisible(false);
        lblInvalidEmail.setVisible(false);
        lblInvalidCustomID.setVisible(false);

        lblAlredyAdded.setVisible(false);
        lblAlredyAddedContact.setVisible(false);
    }

    void onActionGetAllCustom() {
        try {
            ObservableList<CashierCustomerTM> supList = CashierCustomerModel.getAll();
            mainCOMCustomer.setItems(supList);
        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }

    }

    void setCellValuefactory(){
        COMCusId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        COMCustomName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        COMCustContact.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        COMCustAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        COMCusEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }


    public void lblClearAllOnAction(ActionEvent actionEvent) {
       txtCusId.setText("");
       txtCusName.setText("");
       txtCusContact.setText("");
       txtCusAddress.setText("");
       txtCusEmail1.setText("");
    }
}
