package com.WheelHub.WheelHub.repository;

import com.WheelHub.WheelHub.constant.enums.ReviewType;
import com.WheelHub.WheelHub.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByVehicleIdAndReviewType(Long vehicleId, ReviewType type);
}
