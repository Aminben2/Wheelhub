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
public class VehicleImageDTO {

    private Long id;
    private Long vehicleId;
    private String imageUrl;
    private LocalDateTime createdAt;
}
