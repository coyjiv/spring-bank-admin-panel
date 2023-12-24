package com.coyjiv.springbankadminpanel.transfer.Customer;

import com.coyjiv.springbankadminpanel.domain.Customer.Customer;
import com.coyjiv.springbankadminpanel.transfer.DTOMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class CustomerRequestMapper extends DTOMapperFacade<Customer, CustomerDTORequest> {
    public CustomerRequestMapper() {
        super(Customer.class, CustomerDTORequest.class);
    }
}
