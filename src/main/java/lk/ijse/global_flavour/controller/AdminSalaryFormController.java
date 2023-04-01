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

import lk.ijse.global_flavour.dto.AdminSalary;
import lk.ijse.global_flavour.dto.tm.AdminSalaryTM;
import lk.ijse.global_flavour.dto.tm.EmployeeTM;
import lk.ijse.global_flavour.model.AdminSalaryModel;
import java.sql.SQLException;
import java.time.LocalDate;


public class AdminSalaryFormController {

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
    void SalryTableOnMouse(MouseEvent event) {

            TablePosition pos=TBLsalary.getSelectionModel().getSelectedCells().get(0);
            int row=pos.getRow();

            ObservableList<TableColumn<AdminSalaryTM,?>> columns=TBLsalary.getColumns();

            COBEmployeEmpId.setValue(columns.get(0).getCellData(row).toString());
            txtSalaryId.setText(columns.get(1).getCellData(row).toString());
            txtSalaryAmountId.setText(columns.get(2).getCellData(row).toString());
            CBMPayM.setValue(columns.get(3).getCellData(row).toString());

    }

    @FXML
    void salaryDeleteONAct(ActionEvent event) {

    }

    @FXML
    void salarySaveONAct(ActionEvent event) {
        String employeId = String.valueOf(COBEmployeEmpId.getValue());
        String salaryId = txtSalaryId.getText();
        String salaryAmount = txtSalaryAmountId.getText();
        String salaryPayment = String.valueOf(CBMPayM.getValue());

        AdminSalary cus = new AdminSalary(salaryId,employeId,salaryAmount,salaryPayment);

        try {
//
            boolean isSaved = AdminSalaryModel.save(cus);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Data added... !").show();
                onActionGetAllSallary();

            }
        } catch (SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }

    @FXML
    void salaryUpdateONAct(ActionEvent event) {
        String employeId = String.valueOf(COBEmployeEmpId.getValue());
        String empID = txtSalaryId.getText();
        String salaryAmount = txtSalaryAmountId.getText();
        String salaryPayment = String.valueOf(CBMPayM.getValue());

        AdminSalary addSalary=new AdminSalary(employeId,empID, salaryAmount, salaryPayment);
        try {

            boolean isUpdated = AdminSalaryModel.change(addSalary);
            new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
            onActionGetAllSallary();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }

    @FXML
    void searchSalaryBtnOnClick(ActionEvent event) {

    }

    @FXML
    void searchSalaryID(KeyEvent event) {

    }

    @FXML
    public void salIdOnAction(ActionEvent actionEvent) {
        String salidinput = txtSalaryId.getText();
        try {
            AdminSalary itSalary = AdminSalaryModel.search(salidinput);
            if (itSalary != null) {

                System.out.println(itSalary.getEmployId());
                System.out.println(itSalary.getPayment());

                COBEmployeEmpId.setValue(itSalary.getEmployId());
                txtSalaryId.setText(itSalary.getSalaryId());
                txtSalaryAmountId.setText(itSalary.getAmount());
                CBMPayM.setValue(itSalary.getPayment());
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
    }

    @FXML
    void initialize() {
        onActionGetAllEmployeeaddToSalary();
        CBMPayM.getItems().addAll("Cash","Card");
        onActionGetAllSallary();
        setCellValuefactory();
    }
    void setCellValuefactory(){
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
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
        }
    }
    void onActionGetAllEmployeeaddToSalary() {

        try {
            ObservableList<String> EmpList = AdminSalaryModel.getAll();

            COBEmployeEmpId.getItems().addAll(EmpList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
        }
    }
}
