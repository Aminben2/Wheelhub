package com.WheelHub.WheelHub.dto.favoriteDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FavoriteDto {
    private Long userId;
    private Long vehicleId;
}
