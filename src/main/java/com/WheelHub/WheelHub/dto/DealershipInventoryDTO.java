package com.WheelHub.WheelHub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DealershipInventoryDTO {

    private Long id;
    private Long dealershipId;
    private Long vehicleId;
    private int stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
