package com.restapi.controllers;

import com.restapi.models.Events;
import com.restapi.models.Games;
import com.restapi.models.User;
import com.restapi.services.CustomService;
import jdk.jfr.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class EventsController {
    private static final Logger logger = LoggerFactory.getLogger(EventsController.class);
    private CustomService<Events> _eventsService;
    private CustomService<Games> _gamesService;
    private CustomService<User> _userService;

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

    @PostMapping("/addEvent/{date}, {description}, {gameId}, {userId}")
    public ResponseEntity<Events> addEvent(@RequestBody Events event,
                                           @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
                                           @PathVariable String description,
                                           @PathVariable int gameId,
                                           @PathVariable int userId) {
        try {
            if (event.getId() != 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Set the userIds in the Events object
            event.setUserIds(Collections.singletonList(userId));

            // Set the gameIds in the Events object
            //event.setGameIds(Collections.singletonList(gameId));

            Events savedEvent = _eventsService.save(event);

            return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-event")
    public Events updateEvent(@RequestBody Events event) {

        return _eventsService.save(event);
    }

    @DeleteMapping("/delete-event")
    public ResponseEntity<Events> deleteEvent(@RequestBody Events event) {
        try {
            Events eventInDb = _eventsService.findById(event.getId());

            if (eventInDb == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            _eventsService.deleteById(event.getId());

            return new ResponseEntity<>(eventInDb, HttpStatus.OK);
        }
         catch (NoSuchElementException e) {
            logger.warn("Event with ID {} not found", event.getId(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

         catch (Exception e) {
            logger.error("Exception occurred while fetching event with ID " + event.getId(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
