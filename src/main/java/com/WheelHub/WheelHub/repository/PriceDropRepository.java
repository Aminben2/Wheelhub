package com.WheelHub.WheelHub.repository;

import com.WheelHub.WheelHub.entity.PriceDrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceDropRepository extends JpaRepository<PriceDrop, Long> {
}
