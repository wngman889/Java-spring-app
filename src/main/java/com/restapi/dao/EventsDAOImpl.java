package com.restapi.dao;

import com.restapi.mappers.EventsMapper;
import com.restapi.models.Events;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventsDAOImpl implements CustomDAO<Events> {
    private EntityManager _entityManager;

    private EventsMapper _eventsMapper;

    @Autowired
    public EventsDAOImpl(EntityManager _entityManager, EventsMapper _eventsMapper) {
        this._entityManager = _entityManager;
        this._eventsMapper = _eventsMapper;
    }

    @Override
    public List<Events> findAll() {
        TypedQuery<Events> typedQuery = _entityManager.createQuery("from Events", Events.class);

        return typedQuery.getResultList();
    }

    @Override
    public Events findById(int id) {

        return _entityManager.find(Events.class, id);
    }

    @Override
    public Events save(Events saveInDB) {

        Events mappedEvents = _eventsMapper.eventsToEvents(saveInDB);

        // Perform the merge with the converted Events
        return _entityManager.merge(mappedEvents);
    }

    @Override
    public void deleteById(int id) {
        Events deleteEvent = _entityManager.find(Events.class, id);

        _entityManager.remove(deleteEvent);
    }
}
