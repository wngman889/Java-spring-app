package com.restapi.repositories;

import com.restapi.models.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GamesRepository extends JpaRepository<Games, Integer> {

    @Query(
            value = "SELECT event_id FROM events_games WHERE game_id = :gameId",
            nativeQuery = true
    )
    List<Integer> findEventIdsByGameId(@Param("gameId") int gameId);
}
