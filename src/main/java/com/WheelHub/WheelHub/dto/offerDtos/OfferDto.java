package com.WheelHub.WheelHub.dto.offerDtos;

import com.WheelHub.WheelHub.constant.enums.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OfferDto {
    private Long vehicleId;
    private Long buyerId;
    private Long sellerId;
    private BigDecimal offerAmount;
    private OfferStatus status;
}

