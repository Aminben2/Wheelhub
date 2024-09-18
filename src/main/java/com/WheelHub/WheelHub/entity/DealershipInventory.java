package com.WheelHub.WheelHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "dealership_inventory")
public class DealershipInventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "dealership_id")
  @NotNull(message = "Dealership must not be null")
  private Dealership dealership;

  @ManyToOne
  @JoinColumn(name = "vehicle_id")
  @NotNull(message = "Vehicle must not be null")
  private Vehicle vehicle;

  @Column(name = "stock")
  @NotNull(message = "Stock must not be null")
  @Min(value = 0, message = "Stock cannot be negative")
  private Integer stock;

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
