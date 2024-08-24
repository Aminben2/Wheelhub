package com.WheelHub.WheelHub.dto;

import com.WheelHub.WheelHub.constant.enums.PaymentMethod;
import com.WheelHub.WheelHub.constant.enums.PaymentStatus;
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
public class PaymentDTO {

    private Long id;
    private Long userId;
    private Long vehicleId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private LocalDateTime createdAt;
}
