package com.WheelHub.WheelHub.entity;

import com.WheelHub.WheelHub.constant.enums.AppointmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
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
@Table(name = "appointments")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @NotNull(message = "User cannot be null")
  private User user;

  @ManyToOne
  @JoinColumn(name = "vehicle_id")
  @NotNull(message = "Vehicle cannot be null")
  private Vehicle vehicle;

  @Enumerated(EnumType.STRING)
  @Column(name = "appointment_type")
  @NotNull(message = "Appointment type cannot be null")
  private AppointmentType appointmentType;

  @Column(name = "scheduled_at")
  @NotNull(message = "Scheduled date and time cannot be null")
  @Future(message = "Scheduled date and time must be in the future")
  private LocalDateTime scheduledAt;

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
