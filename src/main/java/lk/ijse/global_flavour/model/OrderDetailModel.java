package lk.ijse.global_flavour.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import lk.ijse.global_flavour.dto.OrderCartDTO;
import lk.ijse.global_flavour.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailModel {
    public static boolean save(String oId, List<OrderCartDTO> orderDTOList) throws SQLException {
        for (OrderCartDTO dto : orderDTOList){
            if(!save(oId,dto)){
                return false;
            }
        }
        return true;
    }

    private static boolean save(String oId, OrderCartDTO dto) throws SQLException {
        String sql = "INSERT INTO orderdetail(orderId, itemCode, qty, unitPrice)" +
                "VALUES(?, ?, ?, ?)";

        return CrudUtil.execute(
                sql,
                oId,
                dto.getCode(),
                dto.getQty(),
                dto.getUnitPrice()
        );
    }

    public static ObservableList<PieChart.Data> getDataToPieChart() throws SQLException, ClassNotFoundException {
        String sql="SELECT item.itemName,COUNT(orderdetail.orderId) FROM orderDetail INNER JOIN item ON item.itemCode = orderdetail.itemCode INNER JOIN orders\n" +
                " ON orderdetail.orderId=orders.orderId WHERE MONTH(orders.date) = MONTH(CURRENT_DATE()) GROUP BY item.itemName LIMIT 5;\n";
        ObservableList<PieChart.Data> datalist = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute(sql);

        while(resultSet.next()){
            datalist.add(
                    new PieChart.Data(
                            resultSet.getString(1),
                            resultSet.getInt(2)
                    )
            );
        }
        return datalist;

    }
}
