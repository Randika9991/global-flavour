package lk.ijse.global_flavour.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.global_flavour.db.DBConnection;
import lk.ijse.global_flavour.dto.Suppliers;
import lk.ijse.global_flavour.dto.tm.EmployeeTM;
import lk.ijse.global_flavour.dto.tm.SuppliersTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuppliersModel {
    public static boolean save(Suppliers supAdd) throws SQLException {  //data baes ekata dana set eka

        String sql = "INSERT INTO supplier(supId, SupName, Address, email ,contactNo) " +
                "VALUES(?, ?, ?, ? ,?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, supAdd.getSupplierId());
            pstm.setString(2, supAdd.getSupplierName());
            pstm.setString(3, supAdd.getSupplierAddress());
            pstm.setString(4, supAdd.getSupplierEmail());
            pstm.setString(5, supAdd.getSupplierCotact());

            return pstm.executeUpdate() > 0;
        }
    }
    public static Suppliers search(String id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supId = ?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                return new Suppliers(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)

                );
            }
            return null;
        }
    }

    public static boolean update(Suppliers supAdd) throws SQLException {

        String sql = "UPDATE supplier SET supName = ?, address = ?, email = ?, contactNo = ? WHERE supId = ?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, supAdd.getSupplierName());
            pstm.setString(2, supAdd.getSupplierAddress());
            pstm.setString(3, supAdd.getSupplierEmail());
            pstm.setString(4, supAdd.getSupplierCotact());
            pstm.setString(5, supAdd.getSupplierId());

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean delete(String id) throws SQLException {

        String sql = "DELETE FROM supplier WHERE supId = ?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, id);
            return pstm.executeUpdate() > 0;
        }
    }

    public static ObservableList<SuppliersTM> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            ResultSet resultSet = pstm.executeQuery();
            ObservableList<SuppliersTM> dataList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                dataList.add(new SuppliersTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            return dataList;
        }
    }
}
