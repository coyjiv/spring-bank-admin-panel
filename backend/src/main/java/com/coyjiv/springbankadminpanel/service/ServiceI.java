package com.coyjiv.springbankadminpanel.service;

import java.util.List;
import java.util.Map;

public interface ServiceI<T>{
    T save(T object);
    boolean delete(T obj);
    void deleteAll(List<T> entities);
    void saveAll(List<T> entities);
    List<T> findAll();
    boolean deleteById(long id);
    T getOne(long id);

    boolean edit(Map<String, String> json);

    boolean edit(T obj);
}
