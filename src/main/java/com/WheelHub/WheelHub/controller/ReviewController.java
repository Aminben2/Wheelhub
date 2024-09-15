package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.reviewDtos.ReviewDto;
import com.WheelHub.WheelHub.dto.reviewDtos.ReviewResponseDto;
import com.WheelHub.WheelHub.service.impl.ReviewServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('ADMIN','BUYER','SELLER')")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('reviews:create')")
    public ResponseEntity<ReviewResponseDto> createReview(
            @Valid @RequestBody ReviewDto reviewDTO) {
        try {
            ReviewResponseDto createdReview = reviewService.createReview(reviewDTO);
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('reviews:read')")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable @Min(1) Long id) {
        try {
            ReviewResponseDto reviewDTO = reviewService.getReviewById(id);
            return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehicle/{id}/{type}")
    @PreAuthorize("hasAuthority('reviews:read')")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsForVehicle(
            @PathVariable("id") @Min(1) Long id,
            @PathVariable("type") String type
    )
    {
        try {
            List<ReviewResponseDto> reviews = reviewService.getAllReviewsForVehicle(id, type);
             if (reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('reviews:read')")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        try {
            List<ReviewResponseDto> reviews = reviewService.getAllReviews();
            if (reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('reviews:update')")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable @Min(1) Long id,@Valid @RequestBody ReviewDto reviewDTO) {
        try {
            ReviewResponseDto updatedReview = reviewService.updateReview(id, reviewDTO);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('reviews:delete')")
    public ResponseEntity<Void> deleteReview(@PathVariable @Min(1) Long id) {
        try {
            reviewService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

