package com.restapi.repositories;

import com.restapi.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {

    @Query(
            value = "SELECT game_id FROM games_reviews_recommendations WHERE review_id = :reviewId",
            nativeQuery = true
    )
    List<Integer> findGameIdsByReviewId(@Param("reviewId") int reviewId);
}
