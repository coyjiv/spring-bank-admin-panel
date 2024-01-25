package com.coyjiv.springbankadminpanel.service;

import com.coyjiv.springbankadminpanel.domain.Employer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployerService implements ServiceI<Employer>{


    @Override
    public Employer save(Employer object) {
        return null;
    }

    @Override
    public void delete(Employer obj) {

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
    public void deleteById(long id) {

    }

    @Override
    public Optional<Employer> getOne(long id) {
        return Optional.empty();
    }

    @Override
    public void edit(Employer obj) {

    }
}
