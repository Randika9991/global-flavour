package lk.ijse.global_flavour.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.global_flavour.db.DBConnection;
import lk.ijse.global_flavour.dto.AdminSalary;
import lk.ijse.global_flavour.dto.Delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryModel {
    public static boolean save(Delivery delivery) throws SQLException {  //data baes ekata dana set eka
        String sql = "INSERT INTO delivery(deliveryId,empId,orderId,vehiId,location,deliveryDate,dueDate,deliveryStatus) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, delivery.getDeliverId());
            pstm.setString(2, delivery.getEmpId());
            pstm.setString(3, delivery.getOrderId());
            pstm.setString(4, delivery.getVehicalId());
            pstm.setString(5, delivery.getLocation());
            pstm.setString(6, delivery.getDeliverDate());
            pstm.setString(7, String.valueOf(delivery.getDueDate()));
            pstm.setBoolean(8, true);

            return pstm.executeUpdate() > 0;
        }
    }
    public static String getNextDeliverId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT deliveryId FROM delivery ORDER BY deliveryId DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("DV0");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "DV0" + id;
        }
        return "DV001";
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
    public static ObservableList<String> getAllVeId() throws SQLException {
        String sql = "SELECT vehiId from vehicle";

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
}
