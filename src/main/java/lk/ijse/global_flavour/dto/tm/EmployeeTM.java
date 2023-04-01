package lk.ijse.global_flavour.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeTM {
    private String employeeId;
    private String employeeName;
    private String Address;
    private String DOB;
    private String cotactNo;
    private String email;
    private String nic;
    private String jobTittle;

    public EmployeeTM(String string) {
        this.employeeId=string;
    }


}
