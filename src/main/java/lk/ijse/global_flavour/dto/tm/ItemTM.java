package lk.ijse.global_flavour.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ItemTM {
    private String itemCode;
    private String itemName;
    private String unitPrice;
    private String category;
    private String Qty;
}
