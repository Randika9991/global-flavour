package lk.ijse.global_flavour.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.global_flavour.db.DBConnection;
import lk.ijse.global_flavour.dto.AdminSalary;
import lk.ijse.global_flavour.dto.EmployeeSetAndGet;
import lk.ijse.global_flavour.dto.tm.AdminSalaryTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminSalaryModel {
    public static boolean save(AdminSalary SalaryAdd) throws SQLException {  //data baes ekata dana set eka
        String sql = "INSERT INTO salary(salaryId,empId,amount,paymentmethod) " +
                "VALUES(?, ?, ?, ?)";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, SalaryAdd.getSalaryId());
            pstm.setString(2, SalaryAdd.getEmployId());
            pstm.setString(3, SalaryAdd.getAmount());
            pstm.setString(4, SalaryAdd.getPayment());



            return pstm.executeUpdate() > 0;
        }
    }

    public static ObservableList<String> getAll() throws SQLException {
        String sql = "SELECT empId from employee";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            ResultSet resultSet = pstm.executeQuery();

            ObservableList<String> dataList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                dataList.add(new String(
                        resultSet.getString(1)

                ));

            }
            return dataList;
        }

    }

    public static ObservableList<AdminSalaryTM> getAllSalary() throws SQLException {
        String sql = "SELECT * FROM salary";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            ResultSet resultSet = pstm.executeQuery();

            ObservableList<AdminSalaryTM> dataList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                dataList.add(new AdminSalaryTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));

            }
            return dataList;
        }

    }

    public static boolean change(AdminSalary addSalary) throws SQLException {
        String sql = "UPDATE salary SET empId = ?,amount = ?,paymentmethod = ? WHERE salaryId = ?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, addSalary.getEmployId());
            pstm.setString(2, addSalary.getAmount());
            pstm.setString(3, addSalary.getPayment());
            pstm.setString(4, addSalary.getSalaryId());


            return pstm.executeUpdate() > 0;
        }
    }

//    public static AdminSalary search(String id) throws SQLException {
//        String sql = "SELECT * FROM salary WHERE salaryId = ?";
//
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, id);
//
//            ResultSet resultSet = pstm.executeQuery();
//            if(resultSet.next()) {
//                return new AdminSalary(
//                        resultSet.getString(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getString(4)
//                );
//            }
//            return null;
//        }
//    }
   public static AdminSalary search(String salId) throws SQLException {
        String sql = "SELECT * FROM salary WHERE salaryId = ?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

        pstm.setString(1, salId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new AdminSalary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
        }
   }

}
