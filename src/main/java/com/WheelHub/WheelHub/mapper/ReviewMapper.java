package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.reviewDtos.ReviewDto;
import com.WheelHub.WheelHub.entity.Review;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
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

    public static Review dtoToEntity(ReviewDto reviewDTO) {
        Review review = Review.builder()
                .rating(reviewDTO.getRating())
                .comment(reviewDTO.getComment())
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
