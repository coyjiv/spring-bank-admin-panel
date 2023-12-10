package com.coyjiv.springbankadminpanel.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private int age;
    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "CUSTOMEREMPLOYMENT",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "employer_id")
    )
    @ToString.Exclude
    //companies that the customer is working for
    private Set<Employer> employers;

    public Customer(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

}
