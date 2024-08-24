package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    ReviewDTO createReview(ReviewDTO reviewDTO);

    ReviewDTO getReviewById(Long id);

    List<ReviewDTO> getAllReviews();

    ReviewDTO updateReview(Long id, ReviewDTO reviewDTO);

    void deleteReview(Long id);
}
