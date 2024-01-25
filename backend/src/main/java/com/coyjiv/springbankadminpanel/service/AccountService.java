package com.coyjiv.springbankadminpanel.service;

import com.coyjiv.springbankadminpanel.dao.AccountsDao;
import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.domain.Account.Currency;
import com.coyjiv.springbankadminpanel.domain.Customer.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

//@TODO: change types from boolean to void
@Service
public class AccountService implements ServiceI<Account> {
    private final AccountsDao accountDao;

    public AccountService(AccountsDao accountDao){
        this.accountDao = accountDao;
    }

    @Override
    @Transactional
    public Account save(Account object) {
        return accountDao.save(object);
    }


    @Override
    @Transactional
    public void delete(Account obj) {
        accountDao.delete(obj);
    }

    @Override
    @Transactional
    public void deleteAll(List<Account> entities) {
        accountDao.deleteAll(entities);
    }

    @Override
    @Transactional
    public void saveAll(List<Account> entities) {
        accountDao.saveAll(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        accountDao.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Account> getOne(long id) {
        Optional<Account> potentialAcc = accountDao.findById(id);
        return potentialAcc;
    }

    @Transactional
    public void edit(Map<String, String> json, Long id) {
        Optional<Account> accountOptional = accountDao.findById(id);
        if(accountOptional.isEmpty()) return;
        json.forEach((key, value) -> {
                    Field field = ReflectionUtils.findField(Account.class, key);
                    if (field != null) {
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, accountOptional.get(), value);
                    }
                });

        accountDao.save(accountOptional.get());
    }

    @Override
    @Transactional
    public void edit(Account obj) {
        accountDao.save(obj);
    }

    @Transactional(readOnly = true)
    public Account getOne(String number) {
        return accountDao.findAll().stream().filter(account -> account.getNumber().equals(number)).findFirst().orElse(null);
    }

    @Transactional
    public Account createAccount(Currency currency, Customer customer){
        return accountDao.save(new Account(currency, customer));
    }
    @Transactional
    public boolean topUp(String number, double amount){
        Account account = getOne(number);
        if(account != null){
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean withdraw(String accountNumber, double amount) {
        Account account = getOne(accountNumber);
        if(account != null && account.getBalance() >= amount){
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }
    @Transactional
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
    Set<Account> findAllByCustomerId(Long id){
        return accountDao.findAllByCustomerId(id);
    }
}
