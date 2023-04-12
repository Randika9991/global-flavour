package lk.ijse.global_flavour.dto.tm;
import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderTM {
    private String code;
    private String description;
    private  String category;
    private Integer qty;
    private Double unitPrice;
    private Double total;
    private Button btn;
}