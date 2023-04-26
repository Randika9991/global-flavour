package lk.ijse.global_flavour.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.global_flavour.db.DBConnection;
import lk.ijse.global_flavour.dto.AdminSalary;
import lk.ijse.global_flavour.dto.DeliverForm;
import lk.ijse.global_flavour.dto.Delivery;
import lk.ijse.global_flavour.dto.EmployeeSetAndGet;
import lk.ijse.global_flavour.dto.tm.DeliverFormTM;
import lk.ijse.global_flavour.dto.tm.ItemTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryModel {
    public static boolean save(Delivery delivery) throws SQLException {  //data baes ekata dana set eka
        String sql = "INSERT INTO delivery(deliveryId,empId,orderId,vehiId,location,deliveryDate,dueDate) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, delivery.getDeliverId());
            pstm.setString(2, delivery.getEmpId());
            pstm.setString(3, delivery.getOrderId());
            pstm.setString(4, delivery.getVehicalId());
            pstm.setString(5, delivery.getLocation());
            pstm.setString(6, delivery.getDeliverDate());
            pstm.setString(7, String.valueOf(delivery.getDueDate()));

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
            String[] strings = currentId.split("DEL-");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "DEL-" + id;
        }
        return "DEL-001";
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

    public static ObservableList<String> getAllDelivery() throws SQLException {
        String sql = "SELECT deliveryStatus from delivery";

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

    public static ObservableList<DeliverFormTM> getAllDeliveryFromController() throws SQLException {
        String sql = "SELECT * FROM delivery";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            ResultSet resultSet = pstm.executeQuery();

            ObservableList<DeliverFormTM> dataList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                dataList.add(new DeliverFormTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                ));
            }
            return dataList;
        }
    }

    public static boolean change(DeliverForm deliverForm) throws SQLException {

        String sql = "UPDATE delivey SET empId = ?,orderId = ?, vehiId = ?,location = ?, deliveryDate = ?,dueDate = ?,deliveryStatus = ? WHERE deliveryId = ?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, deliverForm.getEmpId());
            pstm.setString(2, deliverForm.getOrderId());
            pstm.setString(3, deliverForm.getVehicalId());
            pstm.setString(4, deliverForm.getLocation());
            pstm.setString(5, String.valueOf(deliverForm.getDeliverDate()));
            pstm.setString(6, String.valueOf(deliverForm.getDueDate()));
            pstm.setString(7, String.valueOf(deliverForm.getDeliverStatus()));
            pstm.setString(8, deliverForm.getDeliverId());

            return pstm.executeUpdate() > 0;
        }
    }

    public static ObservableList<String> getAllVehicalId() throws SQLException {
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
