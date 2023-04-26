package lk.ijse.global_flavour.dto.tm;

import java.time.LocalDate;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class DeliverFormTM {
    private String deliverId;
    private String empId;
    private String orderId;
    private String vehicalId;
    private String location;
    private String deliverDate;
    private String dueDate;
    private String deliverStatus;
    public DeliverFormTM(String deliverId, String empId, String orderId, String vehicalId, String location, String deliverDate, String dueDate, String deliverStatus) {
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
