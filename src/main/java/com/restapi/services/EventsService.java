package com.restapi.services;

import com.restapi.models.Events;

import java.util.List;

public interface EventsService {

    List<Events> findAll();

    Events findById(int id);

    Events save(Events saveEventInDB);

    void deleteById(int id);
}