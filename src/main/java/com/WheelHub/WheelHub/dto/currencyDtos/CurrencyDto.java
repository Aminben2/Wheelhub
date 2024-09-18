package com.WheelHub.WheelHub.dto.currencyDtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CurrencyDto {
  @NotNull(message = "Currency code is mandatory")
  @Size(max = 10, message = "Currency code must be up to 10 characters long")
  private String currencyCode;

  @NotNull(message = "Currency name is mandatory")
  @Size(max = 255, message = "Currency name must be up to 255 characters long")
  private String currencyName;

  @NotNull(message = "Exchange rate is mandatory")
  @DecimalMin(
      value = "0.000000",
      inclusive = false,
      message = "Exchange rate must be greater than zero")
  @DecimalMax(value = "999999.999999", message = "Exchange rate must be less than 1,000,000")
  private BigDecimal exchangeRate;
}
