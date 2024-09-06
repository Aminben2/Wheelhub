package com.WheelHub.WheelHub.dto.savedSearchDtos;

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
public class SavedSearchDto {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotBlank(message = "Search criteria cannot be blank")
    @Size(max = 1000, message = "Search criteria cannot exceed 1000 characters")
    private String searchCriteria;
}
