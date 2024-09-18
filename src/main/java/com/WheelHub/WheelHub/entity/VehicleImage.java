package com.WheelHub.WheelHub.entity;

import jakarta.persistence.*;
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
@Table(name = "vehicle_images")
public class VehicleImage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @NotNull
  @JoinColumn(name = "vehicle_id")
  private Vehicle vehicle;

  @Column(name = "image_url")
  @NotNull
  private String imageUrl;

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
