package com.coyjiv.springbankadminpanel.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Setter
@Getter
@Data
@EqualsAndHashCode(of={"id"})
@Entity
@Table(name = "accounts")
public class Account extends AbstractEntity{
    private String number;
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", length = 3, nullable = false)
    private Currency currency;
    @Column(name = "balance", nullable = false)
    private double balance;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

    public Account(Currency currency, Customer owner) {
        this.number = UUID.randomUUID().toString();
        this.currency = currency;
        this.balance = 0;
        this.owner = owner;
    }

    public Account() {

    }
}
