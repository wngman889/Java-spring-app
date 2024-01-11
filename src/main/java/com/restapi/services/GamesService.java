package com.restapi.services;

import com.restapi.models.Games;

import java.util.List;

public interface GamesService {

    List<Games> findAll();

    Games findById(int id);

    Games save(Games saveGamesInDB);

    void deleteById(int id);
}