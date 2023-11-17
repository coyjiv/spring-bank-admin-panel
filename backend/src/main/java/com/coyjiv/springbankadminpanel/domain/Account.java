package com.coyjiv.springbankadminpanel.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Setter
@Getter
@Data
public class Account {
    private static final AtomicLong count = new AtomicLong(0);
    private Long id;
    private String number;
    private Currency currency;
    private double balance;
    @JsonBackReference
    private Customer owner;

    public Account(Currency currency, Customer owner) {
        this.id = count.incrementAndGet();
        this.number = UUID.randomUUID().toString();
        this.currency = currency;
        this.balance = 0;
        this.owner = owner;
    }
}
