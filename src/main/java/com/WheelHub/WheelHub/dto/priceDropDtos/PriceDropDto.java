package com.WheelHub.WheelHub.dto.priceDropDtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
public class PriceDropDto {

    @NotNull(message = "Vehicle ID is mandatory")
    private Long vehicleId;

    @NotNull(message = "Old price is mandatory")
    @DecimalMin(value = "0.01", message = "Old price must be greater than 0")
    private BigDecimal oldPrice;

    @NotNull(message = "New price is mandatory")
    @DecimalMin(value = "0.01", message = "New price must be greater than 0")
    private BigDecimal newPrice;

    @NotNull(message = "Dropped at date is mandatory")
    @PastOrPresent(message = "Dropped at must be in the past or present")
    private LocalDateTime droppedAt;
}
