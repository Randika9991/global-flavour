package lk.ijse.global_flavour.dto;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Delivery {
    private String deliverId;
    private String empId;
    private String orderId;
    private String vehicalId;
    private String location;
    private String deliverDate;
    private LocalDate dueDate;
    private Boolean deliverStatus;


    public Delivery(String deliverId, String empId, String orderId, String vehicalId, String location, String deliverDate, LocalDate dueDate, Boolean deliverStatus) {
        this.deliverId = deliverId;
        this.empId = empId;
        this.orderId = orderId;
        this.vehicalId = vehicalId;
        this.location = location;
        this.deliverDate = deliverDate;
        this.dueDate = dueDate;
        this.deliverStatus = deliverStatus;
    }

}
