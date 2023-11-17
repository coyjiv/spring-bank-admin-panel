package com.coyjiv.springbankadminpanel.dao;

import com.coyjiv.springbankadminpanel.domain.Account;
import com.coyjiv.springbankadminpanel.domain.Currency;
import com.coyjiv.springbankadminpanel.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CustomerDao implements Dao<Customer> {

    private final List<Customer> customers = new ArrayList<>(
            List.of(
                    new Customer( "John", "test@test.com",32),
                    new Customer( "Lira", "test2@test.com",30)
            ));
    @Override
    public Customer save(Customer object) {
        customers.add(object);
        return object;
    }

    @Override
    public boolean delete(Customer obj) {
        return customers.remove(obj);
    }

    @Override
    public void deleteAll(List<Customer> entities) {
        customers.removeAll(entities);
    }

    @Override
    public void saveAll(List<Customer> entities) {
        customers.addAll(entities);
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public boolean deleteById(long id) {
        return customers.removeIf(customer -> customer.getId() == id);
    }

    @Override
    public Customer getOne(long id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean edit(Map<String, String> json) {
        AtomicLong id = new AtomicLong();
        json.forEach((key, value) -> {
            if (key.equals("id")) {
                id.set(Long.parseLong(value));
            }
        });
        Customer customer = getOne(id.get());
        if (customer == null) {
            return false;
        }
        json.forEach((key, value) -> {
            switch (key) {
                case "name":
                    customer.setName(value);
                    break;
                case "email":
                    customer.setEmail(value);
                    break;
                case "age":
                    customer.setAge(Integer.parseInt(value));
                    break;
            }
        });
        return true;
    }
}
