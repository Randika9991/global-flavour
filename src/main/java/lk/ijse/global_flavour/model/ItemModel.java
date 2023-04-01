package lk.ijse.global_flavour.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.global_flavour.db.DBConnection;
import lk.ijse.global_flavour.dto.Item;
import lk.ijse.global_flavour.dto.Suppliers;
import lk.ijse.global_flavour.dto.tm.ItemTM;
import lk.ijse.global_flavour.dto.tm.SuppliersTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemModel {
    public static boolean save(Item itmAdd) throws SQLException {  //data baes ekata dana set eka
        String sql = "INSERT INTO item(itemCode, ItemName, unitPrice, category ,QtyONHand) " +
                "VALUES(?, ?, ?, ? ,?)";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, itmAdd.getItemCode());
            pstm.setString(2, itmAdd.getItemName());
            pstm.setString(3, itmAdd.getUnitPrice());
            pstm.setString(4, itmAdd.getCategory());
            pstm.setString(5, itmAdd.getQty());

            return pstm.executeUpdate() > 0;
        }
    }
    public static boolean update(Item supAdd) throws SQLException {
        String sql = "UPDATE item SET ItemName = ?, unitPrice = ?, category = ?, QtyONHand = ? WHERE itemCode = ?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, supAdd.getItemName());
            pstm.setString(2, supAdd.getUnitPrice());
            pstm.setString(3, supAdd.getCategory());
            pstm.setString(4, supAdd.getQty());
            pstm.setString(5, supAdd.getItemCode());

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM item WHERE itemCode = ?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, id);

            return pstm.executeUpdate() > 0;
        }
    }

    public static Item search(String id) throws SQLException {
        String sql = "SELECT * FROM item WHERE itemCode = ?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                return new Item(
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
    public static ObservableList<ItemTM> getAll() throws SQLException {
        String sql = "SELECT * FROM item";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            ResultSet resultSet = pstm.executeQuery();

            ObservableList<ItemTM> dataList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                dataList.add(new ItemTM(
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
