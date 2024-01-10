package com.restapi.dao;

import com.restapi.models.Events;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
        TypedQuery<Events> typedQuery = _entityManager.createQuery("from Events", Events.class);

        List<Events> events = typedQuery.getResultList();

        return events;
    }

    @Override
    public Events findById(int id) {
        Events foundEvent = _entityManager.find(Events.class, id);

        return foundEvent;
    }

    @Override
    public Events save(Events saveInDB) {
        Events dbEvent = _entityManager.merge(saveInDB);

        return dbEvent;
    }

    @Override
    public void deleteById(int id) {
        Events deleteEvent = _entityManager.find(Events.class, id);

        _entityManager.remove(deleteEvent);
    }
}
