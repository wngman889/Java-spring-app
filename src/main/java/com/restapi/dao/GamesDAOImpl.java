package com.restapi.dao;

import com.restapi.models.Games;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GamesDAOImpl implements CustomDAO<Games> {
    private EntityManager _entityManager;

    @Autowired
    public GamesDAOImpl(EntityManager _entityManager) {
        this._entityManager = _entityManager;
    }

    @Override
    public List findAll() {
        TypedQuery<Games> typedQuery = _entityManager.createQuery("from Games", Games.class);

        return typedQuery.getResultList();
    }

    @Override
    public Games findById(int id) {
        return _entityManager.find(Games.class, id);
    }

    @Override
    public Games save(Games saveInDB) {
        return _entityManager.merge(saveInDB);
    }

    @Override
    public void deleteById(int id) {
        Games deleteGame = _entityManager.find(Games.class, id);

        _entityManager.remove(deleteGame);
    }
}
