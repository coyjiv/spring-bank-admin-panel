package com.coyjiv.springbankadminpanel.transfer.Account;

import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.domain.Account.AccountStatus;
import com.coyjiv.springbankadminpanel.domain.Account.Currency;
import com.coyjiv.springbankadminpanel.transfer.Customer.CustomerDTORequest;
import com.coyjiv.springbankadminpanel.transfer.DTOMapperFacade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTORequest  {
    private Currency currency;
    private String accountNumber;
    private AccountStatus accountStatus;
    private Double accountBalance;
    private CustomerDTORequest customer;
}
