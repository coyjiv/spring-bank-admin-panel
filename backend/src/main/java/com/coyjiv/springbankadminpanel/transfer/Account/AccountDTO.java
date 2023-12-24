package com.coyjiv.springbankadminpanel.transfer.Account;

import com.coyjiv.springbankadminpanel.domain.Account.AccountStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private String accountType;
    private String currency;
    private Double balance;
    private AccountStatus status;
    private String createdAt;
    private String updatedAt;
    private Long customerId;
}
