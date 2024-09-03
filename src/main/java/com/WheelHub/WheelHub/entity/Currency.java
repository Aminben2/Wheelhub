package com.WheelHub.WheelHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "Currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_code", length = 10, unique = true, nullable = false)
    @NotBlank(message = "Currency code cannot be blank")
    @Size(max = 10, message = "Currency code cannot exceed 10 characters")
    private String currencyCode;

    @Column(name = "currency_name", nullable = false)
    @NotBlank(message = "Currency name cannot be blank")
    @NotNull
    private String currencyName;

    @Column(name = "exchange_rate", precision = 10, scale = 6, nullable = false)
    @NotNull(message = "Exchange rate cannot be null")
    @DecimalMin(value = "0.000000", inclusive = false, message = "Exchange rate must be greater than zero")
    @DecimalMax(value = "999999.999999", message = "Exchange rate is too high")
    @NotNull
    private BigDecimal exchangeRate;

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
