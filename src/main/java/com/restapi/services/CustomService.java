package com.restapi.services;


import java.util.List;

public interface CustomService<T> {
    List<T> findAll();

    T findById(int id);

    T save(T saveInDB);

    void deleteById(int id);
}
