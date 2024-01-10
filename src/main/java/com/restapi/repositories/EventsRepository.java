package com.restapi.repositories;

import com.restapi.models.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, Integer> {
}
