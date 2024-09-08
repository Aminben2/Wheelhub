package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.favoriteDtos.FavoriteDto;
import com.WheelHub.WheelHub.entity.Favorite;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.mapper.FavoriteMapper;
import com.WheelHub.WheelHub.repository.FavoriteRepository;
import com.WheelHub.WheelHub.service.FavoriteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Override
    public FavoriteDto save(FavoriteDto favoriteDto) {
        Favorite favorite = FavoriteMapper.dtoToEntity(favoriteDto);
        favorite = favoriteRepository.save(favorite);
        return FavoriteMapper.entityToDTO(favorite);
    }

    @Override
    public FavoriteDto update(Long id, FavoriteDto favoriteDto) {
        Favorite existingFavorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Favorite not found for id: " + id));

        // Update fields as necessary
        if (favoriteDto.getUserId() != null) {
            User user = new User();
            user.setId(favoriteDto.getUserId());
            existingFavorite.setUser(user);
        }
        if (favoriteDto.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(favoriteDto.getVehicleId());
            existingFavorite.setVehicle(vehicle);
        }

        Favorite updatedFavorite = favoriteRepository.save(existingFavorite);
        return FavoriteMapper.entityToDTO(updatedFavorite);
    }



    @Override
    public FavoriteDto findById(Long id) {
        return favoriteRepository.findById(id)
                .map(FavoriteMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Favorite not found for id: " + id));
    }

    @Override
    public List<FavoriteDto> findAll() {
        return favoriteRepository.findAll().stream()
                .map(FavoriteMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Favorite not found for id: " + id));
        favoriteRepository.delete(favorite);
    }
}
