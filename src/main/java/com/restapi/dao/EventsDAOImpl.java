package com.restapi.dao;

import com.restapi.models.Events;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EventsDAOImpl implements CustomDAO<Events> {
    private EntityManager _entityManager;

    @Autowired
    public EventsDAOImpl(EntityManager _entityManager) {
        this._entityManager = _entityManager;
    }

    @Override
    public List<Events> findAll() {
        return null;
    }

    @Override
    public Events findById(int id) {
        return null;
    }

    @Override
    public Events save(Events saveInDB) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
