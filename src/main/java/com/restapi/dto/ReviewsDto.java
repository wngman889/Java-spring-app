package com.restapi.dto;

import com.restapi.models.Events;
import com.restapi.models.Games;
import com.restapi.models.Reviews;
import com.restapi.models.User;

import java.util.List;

public record ReviewsDto(int id, String reviewTitle, int rating, String reviewDescription, List<Games> games, List<User> users) {
}
