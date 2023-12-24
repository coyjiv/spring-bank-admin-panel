package com.coyjiv.springbankadminpanel.service;

import com.coyjiv.springbankadminpanel.dao.AccountsDao;
import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.domain.Account.Currency;
import com.coyjiv.springbankadminpanel.domain.Customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountService implements ServiceI<Account> {
    private final AccountsDao accountDao;

    public AccountService(AccountsDao accountDao){
        this.accountDao = accountDao;
    }

    @Override
    public Account save(Account object) {
        return accountDao.save(object);
    }

    @Override
    public boolean delete(Account obj) {
        return accountDao.delete(obj);
    }

    @Override
    public void deleteAll(List<Account> entities) {
        accountDao.deleteAll(entities);
    }

    @Override
    public void saveAll(List<Account> entities) {
        accountDao.saveAll(entities);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public boolean deleteById(long id) {
        return accountDao.deleteById(id);
    }

    @Override
    public Account getOne(long id) {
        return accountDao.getOne(id);
    }

    @Override
    public boolean edit(Map<String, String> json) {
        return accountDao.edit(json);
    }

    @Override
    public boolean edit(Account obj) {
        return accountDao.edit(obj);
    }

    public Account getOne(String number) {
        return accountDao.findAll().stream().filter(account -> account.getNumber().equals(number)).findFirst().orElse(null);
    }

    public Account createAccount(Currency currency, Customer customer){
        return accountDao.createAccount(currency, customer);
    }

    public boolean topUp(String number, double amount){
        Account account = getOne(number);
        if(account != null){
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false;
    }


    public boolean withdraw(String accountNumber, double amount) {
        Account account = getOne(accountNumber);
        if(account != null && account.getBalance() >= amount){
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }

    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = getOne(fromAccountNumber);
        Account toAccount = getOne(toAccountNumber);
        if(fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount){
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            return true;
        }
        return false;
    }
}
