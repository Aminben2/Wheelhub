package com.WheelHub.WheelHub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VehicleDTO {

    private Long id;
    private Long sellerId;
    private String make;
    private String model;
    private Integer year;
    private Integer mileage;
    private BigDecimal price;
    private String description;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
