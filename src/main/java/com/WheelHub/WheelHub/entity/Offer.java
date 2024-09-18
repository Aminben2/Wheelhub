package com.WheelHub.WheelHub.entity;

import com.WheelHub.WheelHub.constant.enums.OfferStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
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
@Table(name = "offers")
public class Offer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
  private Vehicle vehicle;

  @ManyToOne
  @JoinColumn(name = "buyer_id", referencedColumnName = "id")
  private User buyer;

  @ManyToOne
  @JoinColumn(name = "seller_id", referencedColumnName = "id")
  private User seller;

  private BigDecimal offerAmount;
  private OfferStatus status;

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
