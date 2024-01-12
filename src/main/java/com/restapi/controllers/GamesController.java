package com.restapi.controllers;

import com.restapi.models.Events;
import com.restapi.models.Games;
import com.restapi.models.Reviews;
import com.restapi.models.User;
import com.restapi.services.CustomService;
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
public class GamesController {
    private static final Logger logger = LoggerFactory.getLogger(GamesController.class);
    private CustomService<Events> _eventsService;
    private CustomService<Games> _gamesService;
    private CustomService<Reviews> _reviewsService;

    @Autowired
    public GamesController(CustomService<Games> _gamesService) {
        this._gamesService = _gamesService;
    }

    @GetMapping("/games")
    public ResponseEntity<List<Games>> findAll() {
        try {
            List<Games> games = _gamesService.findAll();

            if (games.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(games, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Exception occurred while fetching games", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity<Games> getGames(@PathVariable int gameId) {
        try {
            Games game = _gamesService.findById(gameId);

            if (game == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(game, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            logger.warn("Game with ID {} not found", gameId, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            logger.error("Exception occurred while fetching game with ID " + gameId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addGame/{title}, {gameDescription}, {genre}, {reviewsId}, {eventIds}")
    public ResponseEntity<Games> addGame(@RequestBody Games game,
                                           @PathVariable String title,
                                           @PathVariable String description,
                                           @PathVariable String genre,
                                           @PathVariable int reviewId,
                                           @PathVariable int eventId) {
        try {
            if (game.getId() != 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Set the userIds in the Events object
            game.setReviewIds(Collections.singletonList(reviewId));

            // Set the gameIds in the Events object
            game.setEventIds(Collections.singletonList(eventId));

            Games savedGame = _gamesService.save(game);

            return new ResponseEntity<>(savedGame, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-game")
    public Games updateEvent(@RequestBody Games game) {

        return _gamesService.save(game);
    }

    @DeleteMapping("/delete-game")
    public ResponseEntity<Games> deleteGame(@RequestBody Games game) {
        try {
            Games gameInDb = _gamesService.findById(game.getId());

            if (gameInDb == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            _eventsService.deleteById(game.getId());

            return new ResponseEntity<>(gameInDb, HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            logger.warn("Event with ID {} not found", game.getId(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        catch (Exception e) {
            logger.error("Exception occurred while fetching event with ID " + game.getId(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
