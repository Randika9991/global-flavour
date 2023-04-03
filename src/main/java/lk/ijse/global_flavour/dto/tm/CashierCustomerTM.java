package lk.ijse.global_flavour.dto.tm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CashierCustomerTM {
    private String CustomerId;
    private String CustomerName;
    private String ContactNo;
    private String Address;
    private String email;
}
