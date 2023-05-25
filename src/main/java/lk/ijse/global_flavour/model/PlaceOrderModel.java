package lk.ijse.global_flavour.model;

import lk.ijse.global_flavour.db.DBConnection;
import lk.ijse.global_flavour.dto.Delivery;
import lk.ijse.global_flavour.dto.OrderCartDTO;
import lk.ijse.global_flavour.dto.tm.OrderTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PlaceOrderModel {

    private  static  Delivery delivery;

    public static boolean placeOrder(String oId, String cId, double payment, List<OrderCartDTO> orderDTOList, OrderTM orderTM, boolean delivery) throws SQLException{

        Connection con = null;
        try {

            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = OrderModel.save(oId, cId, payment, LocalDate.now(), LocalTime.now(), orderDTOList,delivery);
            if (isSaved) {
                boolean isUpdate = ItemModel.updateQty(orderDTOList);
                if (isUpdate) {
                    boolean isOrdered = OrderDetailModel.save(oId, orderDTOList);
                    if (isOrdered) {
                        if(delivery){
                            boolean isDelivered = DeliveryModel.save(PlaceOrderModel.delivery);
                            if(isDelivered){
                                con.commit();
                                return true;
                            }
                        }else {
                            con.commit();
                            return true;
                        }
                    }
                }
            }

            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public static void saveDelivery(Delivery cus) {
        delivery = cus;

    }
}
