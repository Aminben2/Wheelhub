package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.ReviewDTO;
import com.WheelHub.WheelHub.entity.Review;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.mapper.ReviewMapper;
import com.WheelHub.WheelHub.repository.ReviewRepository;
import com.WheelHub.WheelHub.repository.UserRepository;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + reviewDTO.getUserId()));
        Vehicle vehicle = vehicleRepository.findById(reviewDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + reviewDTO.getVehicleId()));

        Review review = ReviewMapper.dtoToEntity(reviewDTO);
        review.setUser(user);
        review.setVehicle(vehicle);

        review = reviewRepository.save(review);
        return ReviewMapper.entityToDTO(review);
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Review not found for id:" + id));
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(ReviewMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found for id:" + id));

        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + reviewDTO.getUserId()));
        Vehicle vehicle = vehicleRepository.findById(reviewDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + reviewDTO.getVehicleId()));

        existingReview.setUser(user);
        existingReview.setVehicle(vehicle);
        existingReview.setRating(reviewDTO.getRating());
        existingReview.setComment(reviewDTO.getComment());
        existingReview.setCreatedAt(reviewDTO.getCreatedAt());

        Review updatedReview = reviewRepository.save(existingReview);
        return ReviewMapper.entityToDTO(updatedReview);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found for id:" + id));
        reviewRepository.delete(review);
    }
}
