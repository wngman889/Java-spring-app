package com.restapi.dto;

import com.restapi.models.Events;
import com.restapi.models.Reviews;
import com.restapi.models.User;

import java.util.Date;
import java.util.List;

public record GamesDto(int id, String title, String gameDesc, String genre, List<Reviews> reviews, List<Events> events) {
}
