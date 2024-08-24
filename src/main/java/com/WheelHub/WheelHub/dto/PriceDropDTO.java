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
public class PriceDropDTO {

    private Long id;
    private Long vehicleId;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private LocalDateTime droppedAt;
}
