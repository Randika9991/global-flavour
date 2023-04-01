package lk.ijse.global_flavour.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminSalaryTM {
    private String salaryId;
    private String employId;
    private String amount;
    private String payment;
}
