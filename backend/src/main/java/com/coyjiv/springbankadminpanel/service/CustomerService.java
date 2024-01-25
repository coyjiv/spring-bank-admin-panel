package com.coyjiv.springbankadminpanel.service;

import com.coyjiv.springbankadminpanel.dao.CustomerDao;
import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.domain.Customer.Customer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class CustomerService implements ServiceI<Customer> {

    private final CustomerDao customerDao;
    private final AccountService accountService;

    @Override
    public Customer save(Customer object) {
        return customerDao.save(object);
    }

    @Override
    public void delete(Customer obj) {
        customerDao.delete(obj);
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

    public List<Customer> findAll(int page, int size) {
        Page<Customer> pageList = customerDao.findAll(PageRequest.of(page, size));
        return pageList.getContent();
    }

    @Override
    public void deleteById(long id) {
         customerDao.deleteById(id);
    }

    public Optional<Customer> getOne(long id) {
        return  customerDao.findById(id);
    }

    public void edit(Map<String, String> json, Long id) {
        Optional<Customer> customerOptional = customerDao.findById(id);
        if (customerOptional.isEmpty()) {
            return;
        }
        json.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Customer.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, customerOptional.get(), value);
            }
        });
        customerDao.save(customerOptional.get());
    }

    public void edit(Customer customer){
        customerDao.save(customer);
    }

    public Set<Account> getAccounts(long id){

        return accountService.findAllByCustomerId(id);
    }


    public Map<String, Integer> getPaginationInfo(int page, int size) {
        Page<Customer> pageList = customerDao.findAll(PageRequest.of(page, size));
        return Map.of("totalPages", pageList.getTotalPages(), "totalElements", (int) pageList.getTotalElements(), "currentPage", pageList.getNumber(), "numberOfElements", pageList.getNumberOfElements());
    }
}
