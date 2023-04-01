package lk.ijse.global_flavour.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Item {
    private String itemCode;
    private String itemName;
    private String unitPrice;
    private String category;
    private String Qty;
}
