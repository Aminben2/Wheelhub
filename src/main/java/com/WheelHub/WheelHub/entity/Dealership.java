package com.WheelHub.WheelHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "dealerships")
public class Dealership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    @NotBlank(message = "Dealership name cannot be blank")
    @Size(max = 100, message = "Dealership name cannot exceed 100 characters")
    private String name;

    @Column(name = "location", nullable = false, length = 255)
    @NotBlank(message = "Dealership location cannot be blank")
    @Size(max = 255, message = "Dealership location cannot exceed 255 characters")
    private String location;

    @Column(name = "contact_info")
    @Size(max = 255, message = "Contact info cannot exceed 2000 characters")
    private String contactInfo;

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
