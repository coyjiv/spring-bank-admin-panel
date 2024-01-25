package com.coyjiv.springbankadminpanel.service;

import java.util.List;
import java.util.Optional;

public interface ServiceI<T>{
    T save(T object);
    void delete(T obj);
    void deleteAll(List<T> entities);
    void saveAll(List<T> entities);
    List<T> findAll();
    void deleteById(long id);
    Optional<T> getOne(long id);

    void edit(T obj);
}
