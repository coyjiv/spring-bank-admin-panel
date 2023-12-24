package com.coyjiv.springbankadminpanel.transfer.Account;

import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.transfer.DTOMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class AccountResponseMapper extends DTOMapperFacade<Account, AccountDTOResponse> {
    public AccountResponseMapper() {
        super(Account.class, AccountDTOResponse.class);
    }
}
