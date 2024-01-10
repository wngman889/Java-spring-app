package com.restapi.dao;

import java.util.List;

public interface CustomDAO<T> {
    List<T> findAll();

    T findById(int id);

    T save(T saveInDB);

    void deleteById(int id);
}
