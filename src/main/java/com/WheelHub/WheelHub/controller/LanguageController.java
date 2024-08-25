package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.LanguageDTO;
import com.WheelHub.WheelHub.service.impl.LanguageServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageServiceImpl languageService;

    @PostMapping("/")
    public ResponseEntity<LanguageDTO> createLanguage(@RequestBody LanguageDTO languageDTO) {
        try {
            LanguageDTO createdLanguage = languageService.createLanguage(languageDTO);
            return new ResponseEntity<>(createdLanguage, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating language: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDTO> getLanguageById(@PathVariable Long id) {
        try {
            LanguageDTO languageDTO = languageService.getLanguageById(id);
            return new ResponseEntity<>(languageDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("Language not found: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<LanguageDTO>> getAllLanguages() {
        try {
            List<LanguageDTO> languages = languageService.getAllLanguages();
            return new ResponseEntity<>(languages, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LanguageDTO> updateLanguage(@PathVariable Long id, @RequestBody LanguageDTO languageDTO) {
        try {
            LanguageDTO updatedLanguage = languageService.updateLanguage(id, languageDTO);
            return new ResponseEntity<>(updatedLanguage, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("Language not found: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id) {
        try {
            languageService.deleteLanguage(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            log.error("Language not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
