package com.WheelHub.WheelHub.repository;

import com.WheelHub.WheelHub.entity.VehicleFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleFeatureRepository extends JpaRepository<VehicleFeature, Long> {
    @Query("SELECT v.features FROM Vehicle v WHERE v.id = :vehicleId")
    List<VehicleFeature> findFeaturesByVehicleId(@Param("vehicleId") Long vehicleId);
}
