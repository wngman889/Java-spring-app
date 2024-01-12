package com.restapi.services;

import com.restapi.models.Reviews;
import com.restapi.repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServiceImpl implements CustomService<Reviews> {
    private ReviewsRepository _reviewsRepository;

    @Autowired
    public ReviewsServiceImpl(ReviewsRepository _reviewsRepository) {
        this._reviewsRepository = _reviewsRepository;
    }

    @Override
    public List<Reviews> findAll() {
        return _reviewsRepository.findAll();
    }

    @Override
    public Reviews findById(int id) {
        Optional<Reviews> result = _reviewsRepository.findById(id);

        Reviews review = null;

        if(result.isPresent()) {
            review = result.get();
        }else {
            throw new RuntimeException("Did not find any review with that id: " + id);
        }
        return review;
    }

    @Override
    public Reviews save(Reviews saveInDB) {
        return _reviewsRepository.save(saveInDB);
    }

    @Override
    public void deleteById(int id) {
        _reviewsRepository.deleteById(id);
    }
}
