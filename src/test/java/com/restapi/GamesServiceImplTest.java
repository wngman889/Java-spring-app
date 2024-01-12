package com.restapi;

import com.restapi.models.Games;
import com.restapi.repositories.GamesRepository;
import com.restapi.services.CustomService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GamesServiceImplTest {
    @InjectMocks
    private CustomService<Games> gamesService;

    @Mock
    private GamesRepository gamesRepository;

    @Test
    public void testFindAll() {
        // Arrange
        when(gamesRepository.findAll()).thenReturn(Arrays.asList(new Games(), new Games()));

        // Act
        List<Games> result = gamesService.findAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById_ExistingId() {
        // Arrange
        int gameId = 1;
        when(gamesRepository.findById(gameId)).thenReturn(Optional.of(new Games()));

        // Act
        Games result = gamesService.findById(gameId);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testFindById_NonExistingId() {
        // Arrange
        int nonExistingGameId = 99;
        when(gamesRepository.findById(nonExistingGameId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            gamesService.findById(nonExistingGameId);
        });
    }

    @Test
    public void testSave() {
        // Arrange
        Games gameToSave = new Games();
        when(gamesRepository.save(gameToSave)).thenReturn(gameToSave);

        // Act
        Games result = gamesService.save(gameToSave);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        int gameIdToDelete = 1;

        // Act
        gamesService.deleteById(gameIdToDelete);

        // Assert
        verify(gamesRepository, times(1)).deleteById(gameIdToDelete);
    }
}
