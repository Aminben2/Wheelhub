package com.WheelHub.WheelHub.repository;

import com.WheelHub.WheelHub.entity.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByIsDeletedFalse();

    List<Vehicle> findByIsDeletedTrue();
}
