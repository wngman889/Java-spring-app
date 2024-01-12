package com.restapi;

import com.restapi.models.Events;
import com.restapi.repositories.EventsRepository;
import com.restapi.services.CustomService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventServiceImplTest {
    @InjectMocks
    private CustomService<Events> eventService;

    @Mock
    private EventsRepository eventsRepository;

    @Test
    public void testFindAll() {
        // Arrange
        when(eventsRepository.findAll()).thenReturn(Arrays.asList(new Events(), new Events()));

        // Act
        List<Events> result = eventService.findAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById_ExistingId() {
        // Arrange
        int eventId = 1;
        when(eventsRepository.findById(eventId)).thenReturn(Optional.of(new Events()));

        // Act
        Events result = eventService.findById(eventId);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testFindById_NonExistingId() {
        // Arrange
        int nonExistingEventId = 99;
        when(eventsRepository.findById(nonExistingEventId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            eventService.findById(nonExistingEventId);
        });
    }

    @Test
    public void testSave() {
        // Arrange
        Events eventToSave = new Events();
        when(eventsRepository.save(eventToSave)).thenReturn(eventToSave);

        // Act
        Events result = eventService.save(eventToSave);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        int eventIdToDelete = 1;

        // Act
        eventService.deleteById(eventIdToDelete);

        // Assert
        verify(eventsRepository, times(1)).deleteById(eventIdToDelete);
    }
}
