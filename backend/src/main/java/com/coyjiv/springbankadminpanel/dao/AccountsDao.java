package com.coyjiv.springbankadminpanel.dao;

import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.domain.Account.Currency;
import com.coyjiv.springbankadminpanel.domain.Customer.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface AccountsDao extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.owner.id = ?1")
    Set<Account> findAllByCustomerId(Long id);
//    Set<Account> findAccountsByOwner(Long id);
}
