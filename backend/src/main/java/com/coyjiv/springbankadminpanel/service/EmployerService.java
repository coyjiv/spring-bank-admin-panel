package com.coyjiv.springbankadminpanel.service;

import com.coyjiv.springbankadminpanel.domain.Employer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployerService implements ServiceI<Employer>{
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
