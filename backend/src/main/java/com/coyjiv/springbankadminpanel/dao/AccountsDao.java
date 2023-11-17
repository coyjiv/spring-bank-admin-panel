package com.coyjiv.springbankadminpanel.dao;

import com.coyjiv.springbankadminpanel.domain.Account;
import com.coyjiv.springbankadminpanel.domain.Currency;
import com.coyjiv.springbankadminpanel.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class AccountsDao implements Dao<Account> {

    private final List<Account> accounts = new ArrayList<>();

    @Override
    public Account save(Account object) {
        accounts.add(object);
        return object;
    }

    @Override
    public boolean delete(Account obj) {
        return accounts.remove(obj);
    }

    @Override
    public void deleteAll(List<Account> entities) {
        accounts.removeAll(entities);
    }

    @Override
    public void saveAll(List<Account> entities) {
        accounts.addAll(entities);
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }

    @Override
    public boolean deleteById(long id) {
        return accounts.removeIf(account -> account.getId() == id);
    }

    @Override
    public Account getOne(long id) {
        return accounts.stream().filter(account -> account.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean edit(Map<String, String> json) {
        AtomicLong id = new AtomicLong();
        json.forEach((key, value) -> {
            if (key.equals("id")) {
                id.set(Long.parseLong(value));
            }
        });
        Account account = getOne(id.get());
        if (account == null) {
            return false;
        }
        json.forEach((key, value) -> {
            switch (key) {
                case "accountNumber":
                    account.setNumber(value);
                    break;
                case "currency":
                    account.setCurrency(Currency.valueOf(value));
                    break;
                case "balance":
                    account.setBalance(Double.parseDouble(value));
                    break;
            }
        });
        return true;
    }

    public Account createAccount(Currency currency, Customer customer){
        Account account = new Account(currency, customer);
        return save(account);
    }

    public Account getOne(String number) {
        return accounts.stream().filter(account -> account.getNumber().equals(number)).findFirst().orElse(null);
    }
}
