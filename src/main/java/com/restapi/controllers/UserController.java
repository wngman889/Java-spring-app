package com.restapi.controllers;

import com.restapi.models.Events;
import com.restapi.models.Reviews;
import com.restapi.models.User;
import com.restapi.services.CustomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private CustomService<Reviews> _reviewService;
    private CustomService<Events> _eventsService;
    private CustomService<User> _userService;

    @Autowired
    public UserController(CustomService<User> _userService) {
        this._userService = _userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        try {
            List<User> users = _userService.findAll();

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Exception occurred while fetching users", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        try {
            User user = _userService.findById(userId);

            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            logger.warn("User with ID {} not found", userId, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            logger.error("Exception occurred while fetching user with ID " + userId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            User savedUser = _userService.save(user);

            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-user")
    public User updateUser(@RequestBody User user) {

        return _userService.save(user);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<User> deleteUser(@RequestBody Integer userId) {
        try {
            User userInDb = _userService.findById(userId);

            if (userInDb == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            _userService.deleteById(userId);

            return new ResponseEntity<>(userInDb, HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            logger.warn("User with ID {} not found", userId, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        catch (Exception e) {
            logger.error("Exception occurred while fetching user with ID " + userId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
