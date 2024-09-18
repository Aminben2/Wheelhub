package com.WheelHub.WheelHub.dto.dealershipDtos;

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
public class DealershipDto {

  @NotNull(message = "Name is mandatory")
  @Size(max = 255, message = "Name must be up to 255 characters long")
  private String name;

  @NotNull(message = "Location is mandatory")
  @Size(max = 255, message = "Location must be up to 255 characters long")
  private String location;

  @Size(max = 2000, message = "Contact info must be up to 2000 characters long")
  private String contactInfo;
}
