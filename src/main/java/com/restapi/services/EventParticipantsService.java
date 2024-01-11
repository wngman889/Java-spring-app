package com.restapi.services;

import com.restapi.models.EventParticipants;

import java.util.List;

public interface EventParticipantsService {
    List<EventParticipantsService> findAll();

    EventParticipantsService findById(int id);

    EventParticipantsService save(EventParticipantsService saveEventParticipantsServiceInDB);

    void deleteById(int id);
}
