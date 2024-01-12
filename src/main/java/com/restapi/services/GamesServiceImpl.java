package com.restapi.services;

import com.restapi.models.Games;
import com.restapi.repositories.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GamesServiceImpl implements CustomService<Games> {
    private GamesRepository _gamesRepository;

    @Autowired
    public GamesServiceImpl(GamesRepository _gamesRepository) {
        this._gamesRepository = _gamesRepository;
    }

    @Override
    public List<Games> findAll() {
        return _gamesRepository.findAll();
    }

    @Override
    public Games findById(int id) {
        Optional<Games> result = _gamesRepository.findById(id);

        Games game = null;

        if(result.isPresent()) {
            game = result.get();
        }else {
            throw new RuntimeException("Did not find any game with that id: " + id);
        }
        return game;
    }

    @Override
    public Games save(Games saveInDB) {
        return _gamesRepository.save(saveInDB);
    }

    @Override
    public void deleteById(int id) {
        _gamesRepository.deleteById(id);
    }
}
