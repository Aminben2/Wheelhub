package com.WheelHub.WheelHub.dto.DealershipInventoryDtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DealershipInventoryDto {

    @NotNull(message = "Dealership ID is mandatory")
    private Long dealershipId;

    @NotNull(message = "Vehicle ID is mandatory")
    private Long vehicleId;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;
}
