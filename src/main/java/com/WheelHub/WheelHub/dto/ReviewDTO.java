package com.WheelHub.WheelHub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReviewDTO {

    private Long id;
    private Long userId;
    private Long vehicleId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}
