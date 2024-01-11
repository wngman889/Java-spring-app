package com.restapi.services;

import com.restapi.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    User save(User saveUserInDB);

    void deleteById(int id);
}
