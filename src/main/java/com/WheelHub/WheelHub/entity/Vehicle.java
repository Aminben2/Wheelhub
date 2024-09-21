package com.WheelHub.WheelHub.entity;

import com.WheelHub.WheelHub.constant.enums.AvailabilityStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Vehicles")
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "seller_id")
  private User seller;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private VehicleType vehicleType;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private VehicleCategory category;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "vehicle_feature_mapping",
      joinColumns = @JoinColumn(name = "vehicle_id"),
      inverseJoinColumns = @JoinColumn(name = "feature_id"))
  private Set<VehicleFeature> features = new HashSet<>();

  @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
  private List<VehicleImage> images = new ArrayList<>();

  @Column(name = "make", nullable = false)
  @NotBlank(message = "Make is mandatory")
  @Size(max = 50, message = "Make cannot exceed 50 characters")
  private String make;

  @Column(name = "model", nullable = false)
  @NotBlank(message = "Model is mandatory")
  @Size(max = 50, message = "Model cannot exceed 50 characters")
  private String model;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private AvailabilityStatus status = AvailabilityStatus.IN_STOCK;

  @Column(name = "year")
  @Min(value = 1886, message = "Year must be greater than or equal to 1886")
  @Max(value = 2100, message = "Year must be less than or equal to 2100")
  private Integer year;

  @Column(name = "mileage")
  @Min(value = 0, message = "Mileage must be zero or greater")
  private Integer mileage;

  @Column(name = "price", precision = 10, scale = 2, nullable = false)
  @NotNull(message = "Price is mandatory")
  @DecimalMin(value = "0.00", message = "Price must be greater than or equal to 0.00")
  private BigDecimal price;

  @Column(name = "description")
  @Size(max = 1000, message = "Description cannot exceed 1000 characters")
  private String description;

  @Column(name = "location")
  @Size(max = 255, message = "Location cannot exceed 255 characters")
  private String location;

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
