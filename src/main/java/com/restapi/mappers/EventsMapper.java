package com.restapi.mappers;

import com.restapi.dto.EventsDto;
import com.restapi.models.Events;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventsMapper {
    @Mapping(target = "games", ignore = true)
    @Mapping(target = "users", ignore = true)
    Events eventsToEvents(Events events);

    @Mapping(target = "games", ignore = true)
    @Mapping(target = "users", ignore = true)
    Events eventsDtoToEvents(EventsDto eventsDto);
}
