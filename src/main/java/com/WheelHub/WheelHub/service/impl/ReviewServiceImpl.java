package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.constant.enums.ReviewType;
import com.WheelHub.WheelHub.dto.reviewDtos.ReviewDto;
import com.WheelHub.WheelHub.dto.reviewDtos.ReviewResponseDto;
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
    public ReviewResponseDto createReview(ReviewDto reviewDTO) {
        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + reviewDTO.getUserId()));
        Vehicle vehicle = vehicleRepository.findById(reviewDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + reviewDTO.getVehicleId()));

        Review review = ReviewMapper.dtoToEntity(reviewDTO);

        review = reviewRepository.save(review);
        return ReviewMapper.entityToResponseDTO(review);
    }

    @Override
    public ReviewResponseDto getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewMapper::entityToResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Review not found for id:" + id));
    }

    @Override
    public List<ReviewResponseDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(ReviewMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponseDto> getAllReviewsForVehicle(Long id, String type) {
        ReviewType reviewTypeEnum = ReviewType.valueOf(type.toUpperCase());
        return reviewRepository.findByVehicleIdAndReviewType(id,reviewTypeEnum).stream()
                .map(ReviewMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDto updateReview(Long id, ReviewDto reviewDTO) {
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

        Review updatedReview = reviewRepository.save(existingReview);
        return ReviewMapper.entityToResponseDTO(updatedReview);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found for id:" + id));
        reviewRepository.delete(review);
    }
}
