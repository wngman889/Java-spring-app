package com.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface CustomRepository<T, Integer> extends JpaRepository<T, Integer> {
    List<T> findsAll();

    T findById(int id);

    T saveToDb(T saveToDB);

    void deleteById(int id);
}
