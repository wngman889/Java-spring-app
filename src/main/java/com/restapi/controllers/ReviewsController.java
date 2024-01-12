package com.restapi.controllers;

import com.restapi.models.Games;
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
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ReviewsController {
    private static final Logger logger = LoggerFactory.getLogger(ReviewsController.class);
    private CustomService<User> _userService;
    private CustomService<Games> _gamesService;
    private CustomService<Reviews> _reviewsService;

    @Autowired
    public ReviewsController(CustomService<Reviews> _reviewsService) {
        this._reviewsService = _reviewsService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Reviews>> findAll() {
        try {
            List<Reviews> reviews = _reviewsService.findAll();

            if (reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reviews, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Exception occurred while fetching reviews", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Reviews> getReviews(@PathVariable int reviewId) {
        try {
            Reviews review = _reviewsService.findById(reviewId);

            if (review == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(review, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            logger.warn("Review with ID {} not found", reviewId, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            logger.error("Exception occurred while fetching review with ID " + reviewId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addReview/{reviewTitle}, {rating}, {reviewDescription}, {gameId}, {userId}")
    public ResponseEntity<Reviews> addReview(@RequestBody Reviews review,
                                         @PathVariable String reviewTitle,
                                         @PathVariable String rating,
                                         @PathVariable String reviewDescription,
                                         @PathVariable int gameId,
                                         @PathVariable int userId) {
        try {
            if (review.getId() != 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            review.setGameIds(Collections.singletonList(gameId));

            review.setUserIds(Collections.singletonList(userId));

            Reviews savedReview = _reviewsService.save(review);

            return new ResponseEntity<>(savedReview, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-review")
    public Reviews updateReview(@RequestBody Reviews review) {

        return _reviewsService.save(review);
    }

    @DeleteMapping("/delete-review")
    public ResponseEntity<Reviews> deleteReview(@RequestBody Reviews review) {
        try {
            Reviews reviewInDb = _reviewsService.findById(review.getId());

            if (reviewInDb == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            _reviewsService.deleteById(review.getId());

            return new ResponseEntity<>(reviewInDb, HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            logger.warn("Review with ID {} not found", review.getId(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        catch (Exception e) {
            logger.error("Exception occurred while fetching review with ID " + review.getId(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
