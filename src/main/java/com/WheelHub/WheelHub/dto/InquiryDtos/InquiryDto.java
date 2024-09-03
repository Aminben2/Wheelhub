package com.WheelHub.WheelHub.dto.InquiryDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InquiryDto {

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotNull(message = "Vehicle ID is mandatory")
    private Long vehicleId;

    @NotBlank(message = "Message cannot be blank")
    @Size(max = 500, message = "Message cannot exceed 500 characters")
    private String message;
}
