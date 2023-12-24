package com.coyjiv.springbankadminpanel.domain;

import com.coyjiv.springbankadminpanel.domain.Customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


//Company

@Entity
@Table(name = "employers")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"customers"})
@EqualsAndHashCode(of={"id"})
public class Employer extends AbstractEntity {
    private String name;
    private String address;
    @JsonIgnore
    @ManyToMany(mappedBy = "employers")
    private List<Customer> customers;

    public Employer(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public Employer(Long id, String name, String address) {
        this.setId(id);
        this.name = name;
        this.address = address;
    }
}
