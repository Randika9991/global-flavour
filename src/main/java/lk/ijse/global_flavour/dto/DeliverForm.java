package lk.ijse.global_flavour.dto;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeliverForm {
    private String deliverId;
    private String empId;
    private String orderId;
    private String vehicalId;
    private String location;
    private LocalDate deliverDate;
    private LocalDate dueDate;
    private String deliverStatus;


}
