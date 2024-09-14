package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.reviewDtos.ReviewDto;
import com.WheelHub.WheelHub.dto.reviewDtos.ReviewResponseDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewDto reviewDTO);

    ReviewDto getReviewById(Long id);

    List<ReviewDto> getAllReviews();

    List<ReviewResponseDto> getAllReviewsForVehicle(Long id, String type);

    ReviewDto updateReview(Long id, ReviewDto reviewDTO);

    void deleteReview(Long id);
}
