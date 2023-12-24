package com.coyjiv.springbankadminpanel.transfer.Account;

import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.transfer.DTOMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class AccountRequestMapper extends DTOMapperFacade<Account, AccountDTORequest> {
    public AccountRequestMapper() {
        super(Account.class, AccountDTORequest.class);
    }
}
