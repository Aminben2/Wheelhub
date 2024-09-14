package com.WheelHub.WheelHub.dto.reviewDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReviewResponseDto {
    private Long id ;

    private String username;

    private int rating;

    private String comment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
