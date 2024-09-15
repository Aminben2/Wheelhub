package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.reviewDtos.ReviewDto;
import com.WheelHub.WheelHub.dto.reviewDtos.ReviewResponseDto;

import java.util.List;

public interface ReviewService {

    ReviewResponseDto createReview(ReviewDto reviewDTO);

    ReviewResponseDto getReviewById(Long id);

    List<ReviewResponseDto> getAllReviews();

    List<ReviewResponseDto> getAllReviewsForVehicle(Long id, String type);

    ReviewResponseDto updateReview(Long id, ReviewDto reviewDTO);

    void deleteReview(Long id);
}
