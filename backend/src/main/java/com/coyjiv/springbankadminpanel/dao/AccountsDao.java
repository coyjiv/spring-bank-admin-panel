package com.coyjiv.springbankadminpanel.dao;

import com.coyjiv.springbankadminpanel.domain.Account;
import com.coyjiv.springbankadminpanel.domain.Currency;
import com.coyjiv.springbankadminpanel.domain.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class AccountsDao implements Dao<Account> {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Account save(Account object) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(object);
            transaction.commit();
        } catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
        }
        return object;
    }

    @Override
    public boolean delete(Account obj) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.remove(obj);
            return true;
        } catch (HibernateException e){
            return false;
        }
    }

    @Override
    public void deleteAll(List<Account> entities) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            for (Account account : entities) {
                entityManager.remove(account);
            }
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll(List<Account> entities) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            for (Account account : entities) {
                entityManager.persist(account);
            }
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts;
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            accounts = entityManager.createQuery("from Account a", Account.class).getResultList();
        }
        return accounts;
    }

    @Override
    public boolean deleteById(long id) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.createQuery("delete from Account a where a.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (HibernateException e){
            return false;
        }
    }

    @Override
    public Account getOne(long id) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            return entityManager.find(Account.class, id);
        }
    }

    @Override
    public boolean edit(Map<String, String> json) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            entityManager.createQuery("update Account a set a.number = :number, a.currency = :currency, a.balance = :balance where a.id = :id")
                    .setParameter("number", json.get("accountNumber"))
                    .setParameter("currency", Currency.valueOf(json.get("currency")))
                    .setParameter("balance", Double.parseDouble(json.get("balance")))
                    .executeUpdate();
            return true;
        } catch (HibernateException e){
            return false;
        }
    }

    public Account createAccount(Currency currency, Customer customer){
        Account account = new Account(currency, customer);
        return save(account);
    }

    public Account getOne(String number) {
        try(EntityManager entityManager = entityManagerFactory.createEntityManager()){
            return entityManager.createQuery("from Account a where a.number = :number", Account.class)
                    .setParameter("number", number)
                    .getSingleResult();
        }
    }
}
