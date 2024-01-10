package com.restapi.repositories;

import com.restapi.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {
}
