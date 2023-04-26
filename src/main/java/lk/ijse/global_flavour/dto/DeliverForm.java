package lk.ijse.global_flavour.dto;
import lombok.*;

import java.time.LocalDate;

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
    private Boolean deliverStatus;
    public DeliverForm(String deliverId, String empId, String orderId, String vehicalId, String location, LocalDate deliverDate, LocalDate dueDate, Boolean deliverStatus) {
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
