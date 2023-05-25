package lk.ijse.global_flavour.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.global_flavour.db.DBConnection;
import lk.ijse.global_flavour.dto.CashierVehicle;
import lk.ijse.global_flavour.dto.Item;
import lk.ijse.global_flavour.dto.tm.CashierVehicleTM;
import lk.ijse.global_flavour.dto.tm.ItemTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierVehicleModel {
    public static boolean save(CashierVehicle itmAdd) throws SQLException {  //data baes ekata dana set eka
        String sql = "INSERT INTO vehicle(vehiId, vehiNo, vehiType) " +
                "VALUES(?, ?, ?)";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, itmAdd.getVehicleId());
            pstm.setString(2, itmAdd.getVehicleNo());
            pstm.setString(3, itmAdd.getVehicleType());

            return pstm.executeUpdate() > 0;
        }
    }

    public static CashierVehicle search(String id) throws SQLException {
        String sql = "SELECT * FROM vehicle WHERE vehiId = ?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                return new CashierVehicle(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)

                );
            }
            return null;
        }
    }

    public static boolean update(CashierVehicle vehiAdd) throws SQLException {
        String sql = "UPDATE vehicle SET vehiNo = ?, vehiType = ? WHERE vehiId = ?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, vehiAdd.getVehicleNo());
            pstm.setString(2, vehiAdd.getVehicleType());
            pstm.setString(3, vehiAdd.getVehicleId());

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM vehicle WHERE vehiId = ?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, id);

            return pstm.executeUpdate() > 0;
        }
    }

    public static ObservableList<CashierVehicleTM> getAll() throws SQLException {
        String sql = "SELECT * FROM vehicle";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            ResultSet resultSet = pstm.executeQuery();

            ObservableList<CashierVehicleTM> dataList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                dataList.add(new CashierVehicleTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            return dataList;
        }
    }
}
