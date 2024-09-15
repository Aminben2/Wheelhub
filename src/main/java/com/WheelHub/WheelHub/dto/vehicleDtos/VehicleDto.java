package com.WheelHub.WheelHub.dto.vehicleDtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VehicleDto {

    @NotNull(message = "Seller ID is required")
    private Long sellerId;

    @NotNull
    private Long vehicleTypeId;

    @NotNull
    private Long vehicleCategoryId;

    @NotBlank(message = "Make is required")
    @Size(max = 255, message = "Make must be less than 255 characters")
    private String make;

    @NotBlank(message = "Model is required")
    @Size(max = 255, message = "Model must be less than 255 characters")
    private String model;

    @Min(value = 1886, message = "Year must be after 1885")
    @Max(value = 9999, message = "Year must be a valid year")
    private Integer year;

    @Min(value = 0, message = "Mileage must be a positive number")
    private Integer mileage;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Digits(integer = 8, fraction = 2, message = "Price must be a valid amount with up to 2 decimal places")
    private BigDecimal price;

    @Size(max = 1000, message = "Description must be less than 1000 characters")
    private String description;

    @Size(max = 255, message = "Location must be less than 255 characters")
    private String location;
}
