package com.coyjiv.springbankadminpanel.transfer.Employer;

import com.coyjiv.springbankadminpanel.domain.Employer;
import com.coyjiv.springbankadminpanel.transfer.DTOMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class EmployerRequestMapper extends DTOMapperFacade<Employer, EmployerDTORequest> {
    public EmployerRequestMapper() {
        super(Employer.class, EmployerDTORequest.class);
    }
}
