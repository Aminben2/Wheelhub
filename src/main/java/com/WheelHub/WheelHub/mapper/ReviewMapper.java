package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.ReviewDTO;
import com.WheelHub.WheelHub.entity.Review;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;

public class ReviewMapper {

    public static ReviewDTO entityToDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .userId(review.getUser() != null ? review.getUser().getId() : null)
                .vehicleId(review.getVehicle() != null ? review.getVehicle().getId() : null)
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review dtoToEntity(ReviewDTO reviewDTO) {
        Review review = Review.builder()
                .id(reviewDTO.getId())
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .createdAt(reviewDTO.getCreatedAt())
                .build();

        if (reviewDTO.getUserId() != null) {
            User user = new User();
            user.setId(reviewDTO.getUserId());
            review.setUser(user);
        }
        if (reviewDTO.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(reviewDTO.getVehicleId());
            review.setVehicle(vehicle);
        }

        return review;
    }
}
