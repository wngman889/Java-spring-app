package com.restapi.controllers;

import com.restapi.models.Events;
import com.restapi.services.CustomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class EventsController {
    private static final Logger logger = LoggerFactory.getLogger(EventsController.class);
    private CustomService<Events> _eventsService;

    @Autowired
    public EventsController(CustomService<Events> _eventsService) {
        this._eventsService = _eventsService;
    }

    @GetMapping("/events")
    public ResponseEntity<List<Events>> findAll() {
        try {
            List<Events> events = _eventsService.findAll();

            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception occurred while fetching events", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<Events> getEvent(@PathVariable int eventId) {
        try {
            Events event = _eventsService.findById(eventId);

            if (event == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.warn("Event with ID {} not found", eventId, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Exception occurred while fetching event with ID " + eventId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
