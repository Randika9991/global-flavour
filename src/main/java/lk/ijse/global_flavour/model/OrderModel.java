package lk.ijse.global_flavour.model;

import javafx.scene.chart.XYChart;
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
            String[] strings = currentId.split("ORD-");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "ORD-" + id;
        }
        return "ORD-001";
    }


        public static boolean save(String oId, String cId, double payment, LocalDate date, LocalTime time, List<OrderCartDTO> orderDTOList, boolean delivery) throws SQLException {
            for(OrderCartDTO dto : orderDTOList) {
                if(!save(oId,cId,payment,date,time,dto,delivery)) {

                    return false;

                }
                break;
            }
            return true;
        }
        private static boolean save(String oId, String cId, double payment, LocalDate date, LocalTime time, OrderCartDTO dto, boolean delivery) throws SQLException {

            String sql = "INSERT INTO orders(orderId, custId, payment,time,date,deliveryStatus) VALUES(?, ?, ?, ?, ?,?)";

            return CrudUtil.execute(
                    sql,
                    oId,
                    cId,
                    payment,
                    time,
                    date,
                    delivery

            );
        }

    public static int getTotalSales() throws SQLException, ClassNotFoundException {
        String sql="SELECT count(orderId) FROM orders WHERE date =curdate()";
        ResultSet resultSet= CrudUtil.execute(sql);
        int count=0;
        while (resultSet.next()){
            count=resultSet.getInt(1);
        }
        return count;
    }

    public static XYChart.Series lineChartData() throws SQLException, ClassNotFoundException {
        String sql="SELECT MONTHNAME(date),sum(payment) from orders group by MONTHNAME(date)";
        ResultSet resultSet=CrudUtil.execute(sql);
        XYChart.Series series=new XYChart.Series();
        while (resultSet.next()){
            series.getData().add(new XYChart.Data(resultSet.getString(1),resultSet.getInt(2)));
        }
        return series;

    }
}

