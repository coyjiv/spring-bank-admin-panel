package com.coyjiv.springbankadminpanel.transfer.Employer;

import com.coyjiv.springbankadminpanel.domain.Employer;
import com.coyjiv.springbankadminpanel.transfer.DTOMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class EmployerResponseMapper extends DTOMapperFacade<Employer, EmployerDTOResponse> {
    public EmployerResponseMapper() {
        super(Employer.class, EmployerDTOResponse.class);
    }
}
