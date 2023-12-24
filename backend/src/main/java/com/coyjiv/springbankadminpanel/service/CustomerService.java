package com.coyjiv.springbankadminpanel.service;

import com.coyjiv.springbankadminpanel.dao.CustomerDao;
import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.domain.Customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CustomerService implements ServiceI<Customer> {

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }
    @Override
    public Customer save(Customer object) {
        return customerDao.save(object);
    }

    @Override
    public boolean delete(Customer obj) {
        return customerDao.delete(obj);
    }

    @Override
    public void deleteAll(List<Customer> entities) {
        customerDao.deleteAll(entities);
    }

    @Override
    public void saveAll(List<Customer> entities) {
        customerDao.saveAll(entities);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public boolean deleteById(long id) {
        return customerDao.deleteById(id);
    }

    @Override
    public Customer getOne(long id) {
        return customerDao.getOne(id);
    }

    @Override
    public boolean edit(Map<String, String> json) {
        return customerDao.edit(json);
    }

    public boolean edit(Customer customer){
        return customerDao.edit(customer);
    }

    public Set<Account> getAccounts(long id){
        return customerDao.getAccounts(id);
    }


}
