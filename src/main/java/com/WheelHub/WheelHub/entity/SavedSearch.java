package com.WheelHub.WheelHub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "saved_searches")
public class SavedSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User must not be null") // Ensure user is not null
    private User user;

    @Column(name = "search_criteria")
    @Size(max = 255, message = "Search criteria cannot exceed 1000 characters") // Adjust max size as needed
    private String searchCriteria;

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