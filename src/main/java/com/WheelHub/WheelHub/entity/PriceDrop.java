package com.WheelHub.WheelHub.entity;

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
@Table(name = "price_drops")
public class PriceDrop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @NotNull(message = "Vehicle must not be null") // Ensure vehicle is not null
    private Vehicle vehicle;

    @Column(name = "old_price")
    @NotNull(message = "Old price must not be null") // Ensure old price is not null
    @DecimalMin(value = "0.00", inclusive = true, message = "Old price must be at least 0.00") // Ensure old price is non-negative
    private BigDecimal oldPrice;

    @Column(name = "new_price")
    @NotNull(message = "New price must not be null") // Ensure new price is not null
    @DecimalMin(value = "0.00", inclusive = true, message = "New price must be at least 0.00") // Ensure new price is non-negative
    private BigDecimal newPrice;

    @Column(name = "dropped_at")
    @NotNull(message = "Dropped at must not be null") // Ensure droppedAt is not null
    @PastOrPresent(message = "Dropped at must be in the past or present") // Ensure droppedAt is in the past or present
    private LocalDateTime droppedAt;
}