package com.WheelHub.WheelHub.repository;

import com.WheelHub.WheelHub.entity.VehicleFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleFeatureRepository extends JpaRepository<VehicleFeature, Long> {
}
