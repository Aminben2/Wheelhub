package com.WheelHub.WheelHub.entity;

import com.WheelHub.WheelHub.constant.enums.PaymentMethod;
import com.WheelHub.WheelHub.constant.enums.PaymentStatus;
import jakarta.persistence.*;
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
@Entity
@Table(name = "Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User must not be null")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    @NotNull(message = "Vehicle must not be null")
    private Vehicle vehicle;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    @NotNull(message = "Amount must not be null")
    @DecimalMin(value = "0.00", inclusive = true, message = "Amount must be at least 0.00")
    private BigDecimal amount;

    @Column(name = "payment_method", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment method must not be null")
    private PaymentMethod paymentMethod;

    @Column(name = "status", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment status must not be null")
    private PaymentStatus status;

    @Column(name = "created_at")
    @PastOrPresent(message = "Created at must be in the past or present")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @PastOrPresent(message = "Updated at must be in the past or present")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}