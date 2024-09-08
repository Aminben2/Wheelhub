package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.favoriteDtos.FavoriteDto;
import com.WheelHub.WheelHub.service.FavoriteService;
import jakarta.persistence.EntityNotFoundException;
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
        try {
            FavoriteDto createdFavorite = favoriteService.save(favoriteDto);
            return new ResponseEntity<FavoriteDto>(createdFavorite, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDto> getFavoriteById(@PathVariable @Min(1) Long id) {
        try {
            FavoriteDto favoriteDto = favoriteService.findById(id);
            return new ResponseEntity<>(favoriteDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FavoriteDto>> getAllFavorites() {
        try {
            List<FavoriteDto> favorites = favoriteService.findAll();
            return new ResponseEntity<>(favorites, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FavoriteDto> updateFavorite(@PathVariable @Min(1) Long id, @Valid @RequestBody FavoriteDto favoriteDto) {
        try {
            FavoriteDto updatedFavorite = favoriteService.update(id, favoriteDto);
            return new ResponseEntity<>(updatedFavorite, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable @Min(1) Long id) {
        try {
            favoriteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
