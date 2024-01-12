package com.restapi.services;

import com.restapi.models.Events;
import com.restapi.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements CustomService<Events> {
    private EventsRepository _eventsRepository;

    @Autowired
    public EventServiceImpl(EventsRepository _eventsRepository) {
        this._eventsRepository = _eventsRepository;
    }

    @Override
    public List<Events> findAll() {
        return _eventsRepository.findAll();
    }

    @Override
    public Events findById(int id) {
        Optional<Events> result = _eventsRepository.findById(id);

        Events event = null;

        if(result.isPresent()) {
            event = result.get();
        }else {
            throw new RuntimeException("Did not find any event with that id: " + id);
        }
        return event;
    }

    @Override
    public Events save(Events saveInDB) {
        return _eventsRepository.save(saveInDB);
    }

    @Override
    public void deleteById(int id) {
        _eventsRepository.deleteById(id);
    }
}
