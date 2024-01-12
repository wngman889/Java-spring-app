package com.restapi.repositories;

import com.restapi.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventsRepository extends JpaRepository<Events, Integer> {
    @Query(
            value = "SELECT game_id FROM events_games WHERE event_id = :eventId",
            nativeQuery = true
    )
    List<Integer> findGameIdsByEventId(@Param("eventId") int eventId);
}
