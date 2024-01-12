package com.restapi;

import com.restapi.models.User;
import com.restapi.repositories.UserRepository;
import com.restapi.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        // Arrange
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User(), new User()));

        // Act
        List<User> result = userService.findAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById_ExistingId() {
        // Arrange
        int userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        // Act
        User result = userService.findById(userId);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testFindById_NonExistingId() {
        // Arrange
        int nonExistingUserId = 99;
        when(userRepository.findById(nonExistingUserId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            userService.findById(nonExistingUserId);
        });
    }

    @Test
    public void testSave() {
        // Arrange
        User userToSave = new User();
        when(userRepository.save(userToSave)).thenReturn(userToSave);

        // Act
        User result = userService.save(userToSave);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        int userIdToDelete = 1;

        // Act
        userService.deleteById(userIdToDelete);

        // Assert
        verify(userRepository, times(1)).deleteById(userIdToDelete);
    }
}
