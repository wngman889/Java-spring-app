package com.restapi.services;

import com.restapi.models.Reviews;

import java.util.List;

public interface ReviewsService {
    List<Reviews> findAll();

    Reviews findById(int id);

    Reviews save(Reviews saveReviewsInDB);

    void deleteById(int id);
}