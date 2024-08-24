package com.WheelHub.WheelHub.repository;

import com.WheelHub.WheelHub.entity.DealershipInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealershipInventoryRepository extends JpaRepository<DealershipInventory, Long> {
}
