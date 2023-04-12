package lk.ijse.global_flavour.model;

import lk.ijse.global_flavour.dto.OrderCartDTO;
import lk.ijse.global_flavour.util.CrudUtil;

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
}
