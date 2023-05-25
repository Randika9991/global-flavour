//all added

package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import lk.ijse.global_flavour.dto.AdminSalary;
import lk.ijse.global_flavour.dto.Item;
import lk.ijse.global_flavour.dto.tm.AdminSalaryTM;
import lk.ijse.global_flavour.dto.tm.EmployeeTM;
import lk.ijse.global_flavour.dto.tm.ItemTM;
import lk.ijse.global_flavour.model.AdminSalaryModel;
import lk.ijse.global_flavour.model.CashierCustomerModel;
import lk.ijse.global_flavour.model.ItemModel;
import lk.ijse.global_flavour.util.AlertController;
import lk.ijse.global_flavour.util.ValidateField;
import org.eclipse.jdt.internal.compiler.parser.Scanner;

import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.Predicate;


public class AdminSalaryFormController {
    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private JFXTextField txtSalaryId;

    @FXML
    private JFXTextField txtSalaryAmountId;

    @FXML
    private TableView<AdminSalaryTM> TBLsalary;

    @FXML
    private TableColumn<?, ?> tabColumEmployeID;

    @FXML
    private TableColumn<?, ?> tabColumSalaryID;

    @FXML
    private TableColumn<?, ?> tabColumSalaryAmount;

    @FXML
    private TableColumn<?, ?> tabColumPaymentMethord;

    @FXML
    private JFXButton btnDeleatSalary;

    @FXML
    private JFXButton btnUpdateSalary;

    @FXML
    private JFXButton btnSaveSalary;

    @FXML
    private TextField txtsearchSalary;

    @FXML
    private Button searchBtnSalary;

    @FXML
    private JFXComboBox COBEmployeEmpId;

    @FXML
    private JFXComboBox CBMPayM;

    @FXML
    private Label lblInvalidsalary;

    @FXML
    void salIdOnAction(ActionEvent event) {

    }

    @FXML
    void salarySaveONAct(ActionEvent event) {

        if (txtSalaryId.getText().isEmpty() || txtSalaryAmountId.getText().isEmpty()) {
            AlertController.animationMesseagewrong("Error", "Employee details not saved. \nPlease make sure to fill the request fields.");
        } else {

            if (ValidateField.SalaryCheck(txtSalaryId.getText())) {
                lblInvalidsalary.setVisible(false);
                String employeId = String.valueOf(COBEmployeEmpId.getValue());
                String salaryId = txtSalaryId.getText();
                String salaryAmount = txtSalaryAmountId.getText();
                String salaryPayment = String.valueOf(CBMPayM.getValue());

                AdminSalary cus = new AdminSalary(salaryId, employeId, salaryAmount, salaryPayment);

                try {
//
                    boolean isSaved = AdminSalaryModel.save(cus);
                    if (isSaved) {
                        AlertController.animationMesseageCorect("CONFIRMATION", "Salary Save Success!");
                        onActionGetAllSallary();

                    }
                } catch (SQLException e) {
                    AlertController.animationMesseagewrong("Error", "something went wrong!");
                }
            } else {
                lblInvalidsalary.setVisible(true);
            }

        }

    }

    @FXML
    void salaryUpdateONAct(ActionEvent event) {

        if (txtSalaryId.getText().isEmpty() || txtSalaryAmountId.getText().isEmpty()) {
            AlertController.animationMesseagewrong("Error", "Employee details not saved. \nPlease make sure to fill the request fields.");
        } else {
            String employeId = String.valueOf(COBEmployeEmpId.getValue());
            String salId = txtSalaryId.getText();
            String salaryAmount = txtSalaryAmountId.getText();
            String salaryPayment = String.valueOf(CBMPayM.getValue());

            AdminSalary addSalary = new AdminSalary(salId, employeId, salaryAmount, salaryPayment);
            try {
                boolean isUpdated = AdminSalaryModel.update(addSalary);
                if (isUpdated) {
                    AlertController.animationMesseageCorect("CONFIRMATION", "Salary updated!");
                    onActionGetAllSallary();
                }
            } catch (SQLException throwables) {
                AlertController.animationMesseagewrong("Error", "something went wrong!");
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                AlertController.animationMesseagewrong("Error", "something went wrong!");
                e.printStackTrace();
            }
        }


    }

    @FXML
    void salaryDeleteONAct(ActionEvent event) {

        if (txtSalaryId.getText().isEmpty()) {

        } else {
            boolean ok = AlertController.okconfirmmessage("Are you Sure. Do you wont Delete item");

            if (ok) {
                String code = txtSalaryId.getText();
                try {
                    boolean isDeleted = AdminSalaryModel.delete(code);
                    if (isDeleted) {
                        AlertController.animationMesseageCorect("CONFIRMATION", "Delete Success!");
                        //onActionGetAllCustom();
                    }
                } catch (SQLException e) {
                    AlertController.animationMesseagewrong("Error", "something went wrong!");
                }

            }
        }
    }

    @FXML
    void SalryTableOnMouse(MouseEvent event) {

        TablePosition pos = TBLsalary.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();

        ObservableList<TableColumn<AdminSalaryTM, ?>> columns = TBLsalary.getColumns();

        COBEmployeEmpId.setValue(columns.get(0).getCellData(row).toString());
        txtSalaryId.setText(columns.get(1).getCellData(row).toString());
        txtSalaryAmountId.setText(columns.get(2).getCellData(row).toString());
        CBMPayM.setValue(columns.get(3).getCellData(row).toString());

    }

    @FXML
    void searchSalaryBtnOnClick(ActionEvent event) {
        String id = txtsearchSalary.getText();

        try {
            AdminSalary itSalary = AdminSalaryModel.search(id);
            if (itSalary != null) {
                COBEmployeEmpId.setValue(itSalary.getEmployId());
                txtSalaryId.setText(itSalary.getSalaryId());
                txtSalaryAmountId.setText(itSalary.getAmount());
                CBMPayM.setValue(itSalary.getPayment());
            }
        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error", "something went wrong!");
        }
    }

    @FXML
    void searchSalaryID(KeyEvent event) throws SQLException {
        String searchValue = txtsearchSalary.getText().trim();
        ObservableList<AdminSalaryTM> obList = AdminSalaryModel.getAllSalary();

        if (!searchValue.isEmpty()) {
            ObservableList<AdminSalaryTM> filteredData = obList.filtered(new Predicate<AdminSalaryTM>() {
                @Override
                public boolean test(AdminSalaryTM itemTM) {
                    return String.valueOf(itemTM.getSalaryId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            TBLsalary.setItems(filteredData);
        } else {
            TBLsalary.setItems(obList);
        }
    }

    @FXML
    void initialize() {
        onActionGetAllEmployeeaddToSalary();
        CBMPayM.getItems().addAll("Cash", "Card");
        onActionGetAllSallary();
        setCellValuefactory();
        lblInvalidsalary.setVisible(false);
    }

    void setCellValuefactory() {
        tabColumEmployeID.setCellValueFactory(new PropertyValueFactory<>("employId"));
        tabColumSalaryID.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        tabColumSalaryAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tabColumPaymentMethord.setCellValueFactory(new PropertyValueFactory<>("payment"));
    }

    void onActionGetAllSallary() {
        try {
            ObservableList<AdminSalaryTM> EmpList = AdminSalaryModel.getAllSalary();
            TBLsalary.setItems(EmpList);

        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error", "something went wrong!");
        }
    }

    void onActionGetAllEmployeeaddToSalary() {

        try {
            ObservableList<String> EmpList = AdminSalaryModel.getAll();

            COBEmployeEmpId.getItems().addAll(EmpList);

        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error", "something went wrong!");
        }
    }

    public void lblClearAllOnAction(ActionEvent actionEvent) {
        COBEmployeEmpId.setValue("");
        txtSalaryId.setText("");
        txtSalaryAmountId.setText("");
        CBMPayM.setValue("");
    }





}
