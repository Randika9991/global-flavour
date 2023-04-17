package lk.ijse.global_flavour.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.global_flavour.db.DBConnection;
import lk.ijse.global_flavour.dto.PlaceSupplyLoad;
import lk.ijse.global_flavour.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SupplyModel {
    public static ObservableList<String> getAll() throws SQLException {
        String sql = "SELECT supId from supplier";

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

    public static ObservableList<String> getAllItemCode() throws SQLException {
        String sql = "SELECT itemCode from item";

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

    public static String getSupplierName(String supp_id) throws SQLException {
        String sql = "SELECT supName FROM supplier WHERE supId=?";
        ResultSet resultSet = CrudUtil.execute(sql,supp_id);

        if(resultSet.next()){
            return (new String(
                    resultSet.getString(1)
            ));
        }
        return null;
    }

    public static String getNextSupplyLoadId() throws SQLException {
        String sql = "SELECT loadId FROM SupplyLoadDetail ORDER BY loadId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);

    }
    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("LOAD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "LOAD-" + digit;
        }
        return "LOAD-001";
    }

    public static boolean placeLoad(String loadid, String suppid, String totalprice, List<PlaceSupplyLoad> placeSupplyLoadList) throws SQLException {

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = save(loadid,suppid,totalprice, LocalDate.now(), LocalTime.now(),placeSupplyLoadList);
            if(isSaved) {
                boolean isUpdated = ItemModel.addQty(placeSupplyLoadList);
                if(isUpdated) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    private static boolean save(String loadid, String suppid, String totalprice, LocalDate now, LocalTime now1, List<PlaceSupplyLoad> placeSupplyLoadList) throws SQLException {
        for(PlaceSupplyLoad placeSupplyLoad : placeSupplyLoadList) {
            if(!savesupplyloaddetails(loadid,suppid,totalprice,now,now1,placeSupplyLoad)) {
                return false;
            }
        }
        return true;

    }
    private static boolean savesupplyloaddetails(String loadid, String suppid, String totalprice, LocalDate now, LocalTime now1, PlaceSupplyLoad placeSupplyLoad) throws SQLException {
        String sql = "INSERT INTO SupplyLoadDetail(loadId,supId,itemCode,qty,date,time,price)" +
                "VALUES(?,?,?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                loadid,
                suppid,
                placeSupplyLoad.getItemcode(),
                placeSupplyLoad.getSuppqty(),
                now,
                now1,
                totalprice
        );
    }
}
