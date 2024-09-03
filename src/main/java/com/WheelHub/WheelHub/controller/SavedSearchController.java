package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.savedSearchDtos.SavedSearchDto;
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
    public ResponseEntity<SavedSearchDto> createSavedSearch(@RequestBody SavedSearchDto savedSearchDTO) {
        try {
            SavedSearchDto createdSavedSearch = savedSearchService.createSavedSearch(savedSearchDTO);
            return new ResponseEntity<>(createdSavedSearch, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavedSearchDto> getSavedSearchById(@PathVariable Long id) {
        try {
            SavedSearchDto savedSearchDTO = savedSearchService.getSavedSearchById(id);
            return new ResponseEntity<>(savedSearchDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SavedSearchDto>> getAllSavedSearches() {
        try {
            List<SavedSearchDto> savedSearches = savedSearchService.getAllSavedSearches();
            return new ResponseEntity<>(savedSearches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SavedSearchDto> updateSavedSearch(@PathVariable Long id, @RequestBody SavedSearchDto savedSearchDTO) {
        try {
            SavedSearchDto updatedSavedSearch = savedSearchService.updateSavedSearch(id, savedSearchDTO);
            return new ResponseEntity<>(updatedSavedSearch, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSavedSearch(@PathVariable Long id) {
        try {
            savedSearchService.deleteSavedSearch(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
