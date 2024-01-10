package com.restapi.dao;

import com.restapi.models.Games;
import com.restapi.models.Reviews;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReviewsDAOImpl implements CustomDAO<Reviews> {
    private EntityManager _entityManager;

    @Autowired
    public ReviewsDAOImpl(EntityManager _entityManager) {
        this._entityManager = _entityManager;
    }
    @Override
    public List<Reviews> findAll() {
        TypedQuery<Reviews> typedQuery = _entityManager.createQuery("from Reviews", Reviews.class);

        return typedQuery.getResultList();
    }

    @Override
    public Reviews findById(int id) {
        return _entityManager.find(Reviews.class, id);
    }

    @Override
    public Reviews save(Reviews saveInDB) {
        return _entityManager.merge(saveInDB);
    }

    @Override
    public void deleteById(int id) {
        Reviews deleteReview = _entityManager.find(Reviews.class, id);

        _entityManager.remove(deleteReview);
    }
}
