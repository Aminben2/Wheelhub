package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.languageDtos.LanguageDto;
import com.WheelHub.WheelHub.service.impl.LanguageServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        LanguageDto createdLanguage = languageService.createLanguage(languageDTO);
        return new ResponseEntity<>(createdLanguage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDto> getLanguageById(@PathVariable @Min(1) Long id) {

        LanguageDto languageDTO = languageService.getLanguageById(id);
        return new ResponseEntity<>(languageDTO, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<LanguageDto>> getAllLanguages() {
        List<LanguageDto> languages = languageService.getAllLanguages();
        return new ResponseEntity<>(languages, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LanguageDto> updateLanguage(@PathVariable @Min(1) Long id, @Valid @RequestBody LanguageDto languageDTO) {
        LanguageDto updatedLanguage = languageService.updateLanguage(id, languageDTO);
        return new ResponseEntity<>(updatedLanguage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable @Min(1) Long id) {
        languageService.deleteLanguage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
