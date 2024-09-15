package com.WheelHub.WheelHub.dto.priceDropDtos;

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
public class PriceDropResponseDto{

    private Long id;

    private BigDecimal oldPrice;

    private BigDecimal newPrice;

    private LocalDateTime droppedAt;
}
