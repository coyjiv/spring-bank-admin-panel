package com.coyjiv.springbankadminpanel.transfer.Customer;

import com.coyjiv.springbankadminpanel.domain.Customer.Customer;
import com.coyjiv.springbankadminpanel.transfer.DTOMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class CustomerResponseMapper extends DTOMapperFacade<Customer, CustomerDTOResponse> {
    public CustomerResponseMapper() {
        super(Customer.class, CustomerDTOResponse.class);
    }
}
