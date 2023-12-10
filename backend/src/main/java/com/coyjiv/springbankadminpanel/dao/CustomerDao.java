package com.coyjiv.springbankadminpanel.dao;

import com.coyjiv.springbankadminpanel.domain.Account;
import com.coyjiv.springbankadminpanel.domain.Currency;
import com.coyjiv.springbankadminpanel.domain.Customer;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class CustomerDao implements Dao<Customer> {

    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;

    @Transactional
    @Override
    public Customer save(Customer object) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        }
        return object;
    }

    @Transactional
    @Override
    public boolean delete(Customer obj) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            entityManagerFactory.close();
            return false;
        }
    }

    @Transactional
    @Override
    public void deleteAll(List<Customer> entities) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            for (Customer customer : entities) {
                entityManager.remove(customer);
            }
            entityManager.getTransaction().commit();
        }
    }

    @Transactional
    @Override
    public void saveAll(List<Customer> entities) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            for (Customer customer : entities) {
                entityManager.persist(customer);
            }
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<Customer> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            List<Customer> customers = null;
            EntityGraph entityGraph = entityManager.createEntityGraph("customerWithAccountsAndEmployers");
            try {
                customers = entityManager.createQuery(" from Customer e", Customer.class)
                        .setHint("jakarta.persistence.fetchgraph", entityGraph)
                        .getResultList();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            } finally {
                entityManager.close();
            }
            return customers;
        }
    }

    @Transactional
    @Override
    public boolean deleteById(long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Customer.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public Customer getOne(long id) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.find(Customer.class, id);
        }
    }

    @Transactional
    @Override
    public boolean edit(Map<String, String> json) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            var customer = entityManager.find(Customer.class, Long.parseLong(json.get("id")));
            customer.setName(json.get("name"));
            customer.setEmail(json.get("email"));
            customer.setAge(Integer.parseInt(json.get("age")));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Account> getAccounts(long id) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            var customer = entityManager.find(Customer.class, id);
            return customer.getAccounts();
        }
    }
}
