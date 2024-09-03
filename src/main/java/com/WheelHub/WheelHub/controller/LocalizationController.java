package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.localizationDtos.LocalizationDto;
import com.WheelHub.WheelHub.service.impl.LocalizationServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/localizations")
@RequiredArgsConstructor
public class LocalizationController {

    private final LocalizationServiceImpl localizationService;

    @PostMapping("/")
    public ResponseEntity<LocalizationDto> createLocalization(@RequestBody LocalizationDto localizationDTO) {
        try {
            LocalizationDto createdLocalization = localizationService.createLocalization(localizationDTO);
            return new ResponseEntity<>(createdLocalization, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizationDto> getLocalizationById(@PathVariable Long id) {
        try {
            LocalizationDto localizationDTO = localizationService.getLocalizationById(id);
            return new ResponseEntity<>(localizationDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocalizationDto>> getAllLocalizations() {
        try {
            List<LocalizationDto> localizations = localizationService.getAllLocalizations();
            return new ResponseEntity<>(localizations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalizationDto> updateLocalization(@PathVariable Long id, @RequestBody LocalizationDto localizationDTO) {
        try {
            LocalizationDto updatedLocalization = localizationService.updateLocalization(id, localizationDTO);
            return new ResponseEntity<>(updatedLocalization, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocalization(@PathVariable Long id) {
        try {
            localizationService.deleteLocalization(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

