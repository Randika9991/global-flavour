//save eka haduwa,deleat,update,serch,


package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import lk.ijse.global_flavour.dto.EmployeeSetAndGet;
import lk.ijse.global_flavour.dto.tm.AdminSalaryTM;
import lk.ijse.global_flavour.dto.tm.EmployeeTM;
import lk.ijse.global_flavour.model.AdminSalaryModel;
import lk.ijse.global_flavour.model.EmployeeSetAndGetModel;
import lk.ijse.global_flavour.util.AlertController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class EmployeeFormController {


    @FXML
    private JFXTextField txtEmpId;

    @FXML
    private JFXTextField txtEmpName;

    @FXML
    private JFXTextField txtEmpNic;

    @FXML
    private DatePicker txtEmpDOBBox;

    @FXML
    private JFXTextField txtEmpJobTitle;

    @FXML
    private JFXTextField txtEmpContact;

    @FXML
    private JFXTextField txtEmpAddress;

    @FXML
    private JFXTextField txtEmpEmail;

    @FXML
    private TableView<EmployeeTM> tablEmplyee;


    @FXML
    private TableColumn<?, ?> tablEmployeeID;

    @FXML
    private TableColumn<?, ?> tablEmployeeName;

    @FXML
    private TableColumn<?, ?> tablEmployeeNIC;

    @FXML
    private TableColumn<?, ?> tablEmployeeDOB;

    @FXML
    private TableColumn<?, ?> tablEmployeeJT;

    @FXML
    private TableColumn<?, ?> tablEmployeeContact;

    @FXML
    private TableColumn<?, ?> tablEmployeeAddrsss;

    @FXML
    private TableColumn<?, ?> tablEmployeemail;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnSave;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField txtsearchEmployee;

    @FXML
    void buttonSaveOnACT(ActionEvent actionEvent) {

        String employeeId = txtEmpId.getText();
        String employeeName = txtEmpName.getText();
        String employeeNic = txtEmpNic.getText();
        String employeeDOB = String.valueOf(txtEmpDOBBox.getValue());
        String employeeJobTittle = txtEmpJobTitle.getText();
        String employeeContact = txtEmpContact.getText();
        String employeeAddress = txtEmpAddress.getText();
        String employeeEmail = txtEmpEmail.getText();

        EmployeeSetAndGet cus = new EmployeeSetAndGet(employeeId, employeeName, employeeAddress, employeeDOB,employeeContact,employeeEmail,employeeNic,employeeJobTittle);

        try {
            boolean isSaved = EmployeeSetAndGetModel.save(cus);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Data added... !").show();
                onActionGetAllEmployee();

            }
        } catch (SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

            String employeeId12 = txtEmpId.getText();
            String name = txtEmpName.getText();
            String address = txtEmpAddress.getText();
            String dOB = String.valueOf(txtEmpDOBBox.getValue());
            String contact = txtEmpContact.getText();
            String email = txtEmpEmail.getText();
            String nic = txtEmpNic.getText();
            String jobTittle = txtEmpJobTitle.getText();

            EmployeeSetAndGet employeeSetAndGet = new EmployeeSetAndGet(employeeId12, name, address, dOB, contact, email, nic, jobTittle);
            try {

                boolean isUpdated = EmployeeSetAndGetModel.change(employeeSetAndGet);
                new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
                onActionGetAllEmployee();
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
            }

    }

    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent) {

        if(txtEmpId.getText().isEmpty()){

        }else {
            boolean ok = AlertController.okconfirmmessage("Are you Sure.\nDo you wont Delete item");

            if(ok){
                String code = txtEmpId.getText();

                try {
                    boolean isDeleted = EmployeeSetAndGetModel.delete(code);
                    if (isDeleted) {
                        new Alert(Alert.AlertType.CONFIRMATION, "deleted!").show();
                        onActionGetAllEmployee();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                }
            }
        }



    }

    @FXML
    void searchBtnOnClick(ActionEvent event) {
        String empid = txtsearchEmployee.getText();

        try {
            EmployeeSetAndGet cust = EmployeeSetAndGetModel.search(empid);
            if (cust != null) {
                txtEmpId.setText(cust.getEmployeeId());
                txtEmpName.setText(cust.getEmployeeName());
                txtEmpAddress.setText(cust.getAddress());
                txtEmpDOBBox.setValue(LocalDate.parse(cust.getDOB()));
                txtEmpContact.setText(cust.getCotactNo());
                txtEmpEmail.setText(cust.getEmail());
                txtEmpNic.setText(cust.getNic());
                txtEmpJobTitle.setText(cust.getJobTittle());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
    }

    @FXML
    void employeeMarkOnMouse(MouseEvent event) {
        TablePosition pos=tablEmplyee.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<EmployeeTM,?>> columns=tablEmplyee.getColumns();

        txtEmpId.setText(columns.get(0).getCellData(row).toString());
        txtEmpName.setText(columns.get(1).getCellData(row).toString());
        txtEmpNic.setText(columns.get(2).getCellData(row).toString());
        txtEmpDOBBox.setValue(LocalDate.parse(columns.get(3).getCellData(row).toString()));
        txtEmpJobTitle.setText(columns.get(4).getCellData(row).toString());
        txtEmpContact.setText(columns.get(5).getCellData(row).toString());
        txtEmpAddress.setText(columns.get(6).getCellData(row).toString());
        txtEmpEmail.setText(columns.get(7).getCellData(row).toString());

    }

    @FXML
    void OnActionIDEmplye(ActionEvent event) {
        String empid = txtEmpId.getText();

        try {
            EmployeeSetAndGet cust = EmployeeSetAndGetModel.search(empid);
            if (cust != null) {
                txtEmpId.setText(cust.getEmployeeId());
                txtEmpName.setText(cust.getEmployeeName());
                txtEmpAddress.setText(cust.getAddress());
                txtEmpDOBBox.setValue(LocalDate.parse(cust.getDOB()));
                txtEmpContact.setText(cust.getCotactNo());
                txtEmpEmail.setText(cust.getEmail());
                txtEmpNic.setText(cust.getNic());
                txtEmpJobTitle.setText(cust.getJobTittle());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
        }
    }

    @FXML
    void searchEmployeeID(KeyEvent event) throws SQLException {

        String searchValue=txtsearchEmployee.getText().trim();
        ObservableList<EmployeeTM>obList=EmployeeSetAndGetModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<EmployeeTM> filteredData = obList.filtered(new Predicate<EmployeeTM>(){
                @Override
                public boolean test(EmployeeTM employeetm) {
                    return String.valueOf(employeetm.getEmployeeId()).toLowerCase().contains(searchValue.toLowerCase());        }
            });
            tablEmplyee.setItems(filteredData);} else {
            tablEmplyee.setItems(obList);
        }
    }

    @FXML
    void initialize() {
        setCellValuefactory();
        onActionGetAllEmployee();
    }

    void setCellValuefactory(){
        tablEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tablEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        tablEmployeeNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tablEmployeeDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        tablEmployeeJT.setCellValueFactory(new PropertyValueFactory<>("jobTittle"));
        tablEmployeeContact.setCellValueFactory(new PropertyValueFactory<>("cotactNo"));
        tablEmployeeAddrsss.setCellValueFactory(new PropertyValueFactory<>("address"));
        tablEmployeemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    void onActionGetAllEmployee() {
        try {
            ObservableList<EmployeeTM> EmpList = EmployeeSetAndGetModel.getAll();
            tablEmplyee.setItems(EmpList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
        }

    }
}
