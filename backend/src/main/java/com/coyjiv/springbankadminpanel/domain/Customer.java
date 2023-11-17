package com.coyjiv.springbankadminpanel.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class Customer {
    private static final AtomicLong count = new AtomicLong(0);
    private final Long id;
    private String name;
    private String email;
    private int age;
    @JsonManagedReference
    private List<Account> accounts;

    public Customer(String name, String email, int age) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.email = email;
        this.age = age;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account){
        accounts.add(account);
    }
}
