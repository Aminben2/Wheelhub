package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.savedSearchDtos.SavedSearchDto;
import com.WheelHub.WheelHub.service.impl.SavedSearchServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/saved-searches")
@RequiredArgsConstructor
@Validated
public class SavedSearchController {

  private final SavedSearchServiceImpl savedSearchService;

  @PostMapping("/")
  public ResponseEntity<SavedSearchDto> createSavedSearch(
      @Valid @RequestBody SavedSearchDto savedSearchDTO) {

    SavedSearchDto createdSavedSearch = savedSearchService.createSavedSearch(savedSearchDTO);
    return new ResponseEntity<>(createdSavedSearch, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SavedSearchDto> getSavedSearchById(@PathVariable @Min(1) Long id) {

    SavedSearchDto savedSearchDTO = savedSearchService.getSavedSearchById(id);
    return new ResponseEntity<>(savedSearchDTO, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<SavedSearchDto>> getAllSavedSearches() {
    List<SavedSearchDto> savedSearches = savedSearchService.getAllSavedSearches();
    return new ResponseEntity<>(savedSearches, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SavedSearchDto> updateSavedSearch(
      @PathVariable @Min(1) Long id, @Valid @RequestBody SavedSearchDto savedSearchDTO) {

    SavedSearchDto updatedSavedSearch = savedSearchService.updateSavedSearch(id, savedSearchDTO);
    return new ResponseEntity<>(updatedSavedSearch, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSavedSearch(@PathVariable @Min(1) Long id) {

    savedSearchService.deleteSavedSearch(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
