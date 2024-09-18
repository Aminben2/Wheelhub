package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.favoriteDtos.FavoriteDto;
import com.WheelHub.WheelHub.service.FavoriteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
@Validated
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/")
    public ResponseEntity<FavoriteDto> createFavorite(@Valid @RequestBody FavoriteDto favoriteDto) {
        FavoriteDto createdFavorite = favoriteService.save(favoriteDto);
        return new ResponseEntity<FavoriteDto>(createdFavorite, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDto> getFavoriteById(@PathVariable @Min(1) Long id) {
        FavoriteDto favoriteDto = favoriteService.findById(id);
        return new ResponseEntity<>(favoriteDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FavoriteDto>> getAllFavorites() {
        List<FavoriteDto> favorites = favoriteService.findAll();
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FavoriteDto> updateFavorite(@PathVariable @Min(1) Long id, @Valid @RequestBody FavoriteDto favoriteDto) {
        FavoriteDto updatedFavorite = favoriteService.update(id, favoriteDto);
        return new ResponseEntity<>(updatedFavorite, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable @Min(1) Long id) {
        favoriteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
