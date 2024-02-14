package com.restapi.repositories;

import com.restapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(
            value = "SELECT game_id FROM user_games WHERE user_id = :userId",
            nativeQuery = true
    )
    List<Integer> findGamesIdsByUserId(@Param("userId") int userId);
}
