package com.WheelHub.WheelHub.dto.vehicleDtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VehicleResponseDto {

    private Long id;

    private Long sellerId;

    private String make;

    private String model;

    private Integer year;

    private Integer mileage;

    private BigDecimal price;

    private String description;

    private String location;

    private Long vehicleTypeId;

    private Long vehicleCategoryId;

    private List<Long> features;

    private Boolean available;
}

