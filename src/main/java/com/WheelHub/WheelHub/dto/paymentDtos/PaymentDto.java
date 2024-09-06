package com.WheelHub.WheelHub.dto.paymentDtos;

import com.WheelHub.WheelHub.constant.enums.PaymentMethod;
import com.WheelHub.WheelHub.constant.enums.PaymentStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PaymentDto {

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotNull(message = "Vehicle ID is mandatory")
    private Long vehicleId;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Payment method is mandatory")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Payment status is mandatory")
    private PaymentStatus status;
}
