package com.coyjiv.springbankadminpanel.domain.Customer;

import com.coyjiv.springbankadminpanel.domain.AbstractEntity;
import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.domain.Employer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@EqualsAndHashCode(of={"id"})
@Table(name = "customers")
@NamedEntityGraph(name = "customerWithAccountsAndEmployers",
        attributeNodes = {@NamedAttributeNode("accounts"), @NamedAttributeNode("employers")})
public class Customer extends AbstractEntity {
    private String name;
    private String email;
    private Integer age;
    private String password;
    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "CUSTOMEREMPLOYMENT",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "employer_id")
    )
    @ToString.Exclude
    //companies that the customer is working for
    private Set<Employer> employers;


    private String phone;

    public Customer(String name, String email, int age, String phone, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.password = password;
        this.accounts = new HashSet<>();
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

}
