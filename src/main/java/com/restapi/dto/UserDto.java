package com.restapi.dto;

import com.restapi.models.Events;
import com.restapi.models.Games;
import com.restapi.models.Reviews;
import com.restapi.models.User;

import java.util.Date;
import java.util.List;

public record UserDto(int id, String username, String password, String email,
                      String profileDesc, Date createdAt, String steamId,
                      List<Reviews> reviews, List<Events> events) {
}
