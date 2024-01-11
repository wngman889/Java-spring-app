package com.restapi.dao;

import com.restapi.models.Events;
import com.restapi.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDAOImpl implements CustomDAO<User> {
    private EntityManager _entityManager;

    @Autowired
    public UserDAOImpl(EntityManager _entityManager) {
        this._entityManager = _entityManager;
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> typedQuery = _entityManager.createQuery("from User", User.class);

        return typedQuery.getResultList();
    }

    @Override
    public User findById(int id) {

        return _entityManager.find(User.class, id);
    }

    @Override
    public User save(User saveInDB) {

        return _entityManager.merge(saveInDB);
    }

    @Override
    public void deleteById(int id) {
        User deleteUser = _entityManager.find(User.class, id);

        _entityManager.remove(deleteUser);

    }
}
