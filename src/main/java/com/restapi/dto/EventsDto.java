package com.restapi.dto;

import com.restapi.models.Games;
import com.restapi.models.User;

import java.util.Date;
import java.util.List;

public record EventsDto(int id, Date date, String description, List<User> users, List<Games> games) {
}
