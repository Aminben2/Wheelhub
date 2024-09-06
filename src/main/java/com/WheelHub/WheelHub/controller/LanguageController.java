package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.languageDtos.LanguageDto;
import com.WheelHub.WheelHub.service.impl.LanguageServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
@Validated
public class LanguageController {

    private final LanguageServiceImpl languageService;

    @PostMapping("/")
    public ResponseEntity<LanguageDto> createLanguage(@Valid @RequestBody LanguageDto languageDTO) {
        try {
            LanguageDto createdLanguage = languageService.createLanguage(languageDTO);
            return new ResponseEntity<>(createdLanguage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDto> getLanguageById(@PathVariable @Min(1) Long id) {
        try {
            LanguageDto languageDTO = languageService.getLanguageById(id);
            return new ResponseEntity<>(languageDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<LanguageDto>> getAllLanguages() {
        try {
            List<LanguageDto> languages = languageService.getAllLanguages();
            return new ResponseEntity<>(languages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LanguageDto> updateLanguage(@PathVariable @Min(1) Long id,@Valid @RequestBody LanguageDto languageDTO) {
        try {
            LanguageDto updatedLanguage = languageService.updateLanguage(id, languageDTO);
            return new ResponseEntity<>(updatedLanguage, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable @Min(1) Long id) {
        try {
            languageService.deleteLanguage(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
