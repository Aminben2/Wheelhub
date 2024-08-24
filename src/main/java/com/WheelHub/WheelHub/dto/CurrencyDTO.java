package com.WheelHub.WheelHub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CurrencyDTO {

    private Long id;
    private String currencyCode;
    private String currencyName;
    private BigDecimal exchangeRate;
}
