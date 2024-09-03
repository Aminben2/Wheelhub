package com.WheelHub.WheelHub.dto.languageDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LanguageDto {

    @NotBlank(message = "Language code is mandatory")
    @Size(max = 10, message = "Language code cannot exceed 10 characters")
    private String languageCode;

    @NotBlank(message = "Language name is mandatory")
    @Size(max = 255, message = "Language name cannot exceed 255 characters")
    private String languageName;
}
