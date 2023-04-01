package lk.ijse.global_flavour.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminSalary {
    private String salaryId;
    private String employId;
    private String amount;
    private String payment;
}
