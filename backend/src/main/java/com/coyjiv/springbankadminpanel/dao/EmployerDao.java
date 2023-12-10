package com.coyjiv.springbankadminpanel.dao;

import com.coyjiv.springbankadminpanel.domain.Employer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployerDao implements Dao<Employer>{
    @Override
    public Employer save(Employer object) {
        return null;
    }

    @Override
    public boolean delete(Employer obj) {
        return false;
    }

    @Override
    public void deleteAll(List<Employer> entities) {

    }

    @Override
    public void saveAll(List<Employer> entities) {

    }

    @Override
    public List<Employer> findAll() {
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public Employer getOne(long id) {
        return null;
    }

    @Override
    public boolean edit(Map<String, String> json) {
        return false;
    }
}
