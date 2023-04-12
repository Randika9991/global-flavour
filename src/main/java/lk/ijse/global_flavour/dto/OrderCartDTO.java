package lk.ijse.global_flavour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class OrderCartDTO {
    private String code;
    private int qty;
    private double unitPrice;
    private String deliverStatus;


    public OrderCartDTO(String code, Integer qty, Double unitPrice) {
        this.code=code;
        this.qty=qty;
        this.unitPrice=unitPrice;

    }
}