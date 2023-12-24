package com.coyjiv.springbankadminpanel.transfer.Customer;

import com.coyjiv.springbankadminpanel.domain.Employer;
import com.coyjiv.springbankadminpanel.transfer.Account.AccountDTOResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.util.Date;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTOResponse {
        @JsonView({CustomerDTOResponseView.Single.class, CustomerDTOResponseView.Many.class})
        private Long id;
        @JsonView({CustomerDTOResponseView.Single.class, CustomerDTOResponseView.Many.class})
        private String name;
        @JsonView({CustomerDTOResponseView.Single.class, CustomerDTOResponseView.Many.class})
        private String email;
        @JsonView({CustomerDTOResponseView.Single.class, CustomerDTOResponseView.Many.class})
        private Integer age;

        @JsonIgnoreProperties("owner")
        @JsonView({CustomerDTOResponseView.Single.class, CustomerDTOResponseView.Many.class})
        private Set<AccountDTOResponse> accounts;

        @JsonIgnoreProperties("customers")
        @JsonView({CustomerDTOResponseView.Single.class, CustomerDTOResponseView.Many.class})
        private Set<Employer> employers;

}
