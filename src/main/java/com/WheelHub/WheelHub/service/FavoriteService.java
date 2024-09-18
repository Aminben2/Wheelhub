package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.favoriteDtos.FavoriteDto;
import java.util.List;

public interface FavoriteService {
  FavoriteDto save(FavoriteDto favoriteDto);

  FavoriteDto update(Long id, FavoriteDto favoriteDto);

  FavoriteDto findById(Long id);

  List<FavoriteDto> findAll();

  void deleteById(Long id);
}
