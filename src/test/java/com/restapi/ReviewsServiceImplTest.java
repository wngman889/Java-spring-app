package com.restapi;

import com.restapi.models.Reviews;
import com.restapi.repositories.ReviewsRepository;
import com.restapi.services.CustomService;
import com.restapi.services.ReviewsServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewsServiceImplTest {
    @InjectMocks
    private CustomService<Reviews> reviewsService;

    @Mock
    private ReviewsRepository reviewsRepository;

    @Test
    public void testFindAll() {
        // Arrange
        when(reviewsRepository.findAll()).thenReturn(Arrays.asList(new Reviews(), new Reviews()));

        // Act
        List<Reviews> result = reviewsService.findAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById_ExistingId() {
        // Arrange
        int reviewId = 1;
        when(reviewsRepository.findById(reviewId)).thenReturn(Optional.of(new Reviews()));

        // Act
        Reviews result = reviewsService.findById(reviewId);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testFindById_NonExistingId() {
        // Arrange
        int nonExistingReviewId = 99;
        when(reviewsRepository.findById(nonExistingReviewId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            reviewsService.findById(nonExistingReviewId);
        });
    }

    @Test
    public void testSave() {
        // Arrange
        Reviews reviewToSave = new Reviews();
        when(reviewsRepository.save(reviewToSave)).thenReturn(reviewToSave);

        // Act
        Reviews result = reviewsService.save(reviewToSave);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        int reviewIdToDelete = 1;

        // Act
        reviewsService.deleteById(reviewIdToDelete);

        // Assert
        verify(reviewsRepository, times(1)).deleteById(reviewIdToDelete);
    }
}
