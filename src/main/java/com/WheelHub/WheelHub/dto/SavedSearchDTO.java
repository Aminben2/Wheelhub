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
public class SavedSearchDTO {

    private Long id;
    private Long userId;
    private String searchCriteria;
    private LocalDateTime createdAt;
}
