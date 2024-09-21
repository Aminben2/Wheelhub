package com.WheelHub.WheelHub.repository;

import com.WheelHub.WheelHub.entity.VehicleFeature;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleFeatureRepository extends JpaRepository<VehicleFeature, Long> {
  @Query("SELECT v.features FROM Vehicle v WHERE v.id = :vehicleId")
  List<VehicleFeature> findFeaturesByVehicleId(@Param("vehicleId") Long vehicleId);

  Optional<VehicleFeature> findByFeatureName(String featureName);
}
