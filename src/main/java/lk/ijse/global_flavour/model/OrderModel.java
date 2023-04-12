package lk.ijse.global_flavour.model;

import lk.ijse.global_flavour.db.DBConnection;
import lk.ijse.global_flavour.dto.OrderCartDTO;
import lk.ijse.global_flavour.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderModel {
    public static String getNextOrderId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("D0");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "D0" + id;
        }
        return "D001";
    }


        public static boolean save(String oId, String cId, double payment, LocalDate date, LocalTime time, List<OrderCartDTO> orderDTOList) throws SQLException {
            for(OrderCartDTO dto : orderDTOList) {
                if(!save(oId,cId,payment,date,time,dto)) {

                    return false;

                }
                break;
            }
            return true;
        }
        private static boolean save(String oId,String cId, double payment,LocalDate date,LocalTime time,OrderCartDTO dto) throws SQLException {

            String sql = "INSERT INTO orders(orderId, custId, payment,time,date,deliveryStatus) VALUES(?, ?, ?, ?, ?,?)";

            return CrudUtil.execute(
                    sql,
                    oId,
                    cId,
                    payment,
                    time,
                    date,
                    dto.getDeliverStatus()

            );
        }
}

