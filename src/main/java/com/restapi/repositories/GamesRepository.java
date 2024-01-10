package com.restapi.repositories;

import com.restapi.models.Games;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<Games, Integer> {
}
