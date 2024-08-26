package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.LocalizationDTO;
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
    public ResponseEntity<LocalizationDTO> createLocalization(@RequestBody LocalizationDTO localizationDTO) {
        try {
            LocalizationDTO createdLocalization = localizationService.createLocalization(localizationDTO);
            return new ResponseEntity<>(createdLocalization, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizationDTO> getLocalizationById(@PathVariable Long id) {
        try {
            LocalizationDTO localizationDTO = localizationService.getLocalizationById(id);
            return new ResponseEntity<>(localizationDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocalizationDTO>> getAllLocalizations() {
        try {
            List<LocalizationDTO> localizations = localizationService.getAllLocalizations();
            return new ResponseEntity<>(localizations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalizationDTO> updateLocalization(@PathVariable Long id, @RequestBody LocalizationDTO localizationDTO) {
        try {
            LocalizationDTO updatedLocalization = localizationService.updateLocalization(id, localizationDTO);
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

