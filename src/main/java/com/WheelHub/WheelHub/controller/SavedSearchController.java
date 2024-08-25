package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.SavedSearchDTO;
import com.WheelHub.WheelHub.service.impl.SavedSearchServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/saved-searches")
@RequiredArgsConstructor
public class SavedSearchController {

    private final SavedSearchServiceImpl savedSearchService;

    @PostMapping("/")
    public ResponseEntity<SavedSearchDTO> createSavedSearch(@RequestBody SavedSearchDTO savedSearchDTO) {
        try {
            SavedSearchDTO createdSavedSearch = savedSearchService.createSavedSearch(savedSearchDTO);
            return new ResponseEntity<>(createdSavedSearch, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            log.error("Error creating saved search: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavedSearchDTO> getSavedSearchById(@PathVariable Long id) {
        try {
            SavedSearchDTO savedSearchDTO = savedSearchService.getSavedSearchById(id);
            return new ResponseEntity<>(savedSearchDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("SavedSearch not found: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SavedSearchDTO>> getAllSavedSearches() {
        try {
            List<SavedSearchDTO> savedSearches = savedSearchService.getAllSavedSearches();
            return new ResponseEntity<>(savedSearches, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SavedSearchDTO> updateSavedSearch(@PathVariable Long id, @RequestBody SavedSearchDTO savedSearchDTO) {
        try {
            SavedSearchDTO updatedSavedSearch = savedSearchService.updateSavedSearch(id, savedSearchDTO);
            return new ResponseEntity<>(updatedSavedSearch, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("SavedSearch not found: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSavedSearch(@PathVariable Long id) {
        try {
            savedSearchService.deleteSavedSearch(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            log.error("SavedSearch not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
