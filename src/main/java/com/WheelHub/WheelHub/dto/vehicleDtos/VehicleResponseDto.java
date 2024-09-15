package com.WheelHub.WheelHub.dto.vehicleDtos;

import com.WheelHub.WheelHub.entity.VehicleCategory;
import com.WheelHub.WheelHub.entity.VehicleType;
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

    private List<Long> features;

    private Boolean available;

    private VehicleCategory category;

    private VehicleType type;
}

