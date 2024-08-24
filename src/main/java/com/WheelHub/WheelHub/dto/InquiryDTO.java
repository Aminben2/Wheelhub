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
public class InquiryDTO {

    private Long id;
    private Long userId;
    private Long vehicleId;
    private String message;
    private LocalDateTime createdAt;
}
