package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.favoriteDtos.FavoriteDto;
import com.WheelHub.WheelHub.entity.Favorite;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapper {

    public static FavoriteDto entityToDTO(Favorite favorite) {
        return FavoriteDto.builder()
                .userId(favorite.getUser() != null ? favorite.getUser().getId() : null)
                .vehicleId(favorite.getVehicle() != null ? favorite.getVehicle().getId() : null)
                .build();
    }

    public static Favorite dtoToEntity(FavoriteDto favoriteDto) {
        Favorite favorite = Favorite.builder()
                .build();

        if (favoriteDto.getUserId() != null) {
            User user = new User();
            user.setId(favoriteDto.getUserId());
            favorite.setUser(user);
        }
        if (favoriteDto.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(favoriteDto.getVehicleId());
            favorite.setVehicle(vehicle);
        }

        return favorite;
    }
}
