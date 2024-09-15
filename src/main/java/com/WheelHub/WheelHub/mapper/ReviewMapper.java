package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.reviewDtos.ReviewDto;
import com.WheelHub.WheelHub.dto.reviewDtos.ReviewResponseDto;
import com.WheelHub.WheelHub.dto.userDtos.UserResponseDtoForGetByUsername;
import com.WheelHub.WheelHub.entity.Review;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
public class ReviewMapper {

    public static ReviewDto entityToDTO(Review review) {
        return ReviewDto.builder()
                .userId(review.getUser() != null ? review.getUser().getId() : null)
                .vehicleId(review.getVehicle() != null ? review.getVehicle().getId() : null)
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }

    public static ReviewResponseDto entityToResponseDTO(Review review) {
        return ReviewResponseDto.builder()
                .id(review.getId())
                .username(review.getUser() != null ? review.getUser().getUsername() : null)
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .reviewType(review.getReviewType())
                .build();
    }

    public static Review dtoToEntity(ReviewDto reviewDTO) {
        Review review = Review.builder()
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
                .reviewType(reviewDTO.getReviewType())
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
