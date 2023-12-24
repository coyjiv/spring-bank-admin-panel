package com.coyjiv.springbankadminpanel.transfer.Account;

import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.transfer.Customer.CustomerDTOResponse;
import com.coyjiv.springbankadminpanel.transfer.Customer.CustomerDTOResponseView;
import com.coyjiv.springbankadminpanel.transfer.DTOMapperFacade;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "owner")
@JsonView({CustomerDTOResponseView.Single.class, CustomerDTOResponseView.Many.class})
public class AccountDTOResponse  {
    private Long id;
    private String number;
    private CustomerDTOResponse owner;
    private Double balance;
}
