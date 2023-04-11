package lk.ijse.global_flavour.dto.tm;
import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartTM {
    private String itemCode;
    private String itemName;
    private String category;
    private Integer qty;
    private Double unitPrice;
    private Double total;
    private Button Action;
}
