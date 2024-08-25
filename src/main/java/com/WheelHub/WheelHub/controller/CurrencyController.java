package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.CurrencyDTO;
import com.WheelHub.WheelHub.service.impl.CurrencyServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyServiceImpl currencyService;

    @PostMapping("/")
    public ResponseEntity<CurrencyDTO> createCurrency(@RequestBody CurrencyDTO currencyDTO) {
        try {
            CurrencyDTO createdCurrency = currencyService.createCurrency(currencyDTO);
            return new ResponseEntity<>(createdCurrency, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating currency: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDTO> getCurrencyById(@PathVariable Long id) {
        try {
            CurrencyDTO currencyDTO = currencyService.getCurrencyById(id);
            return new ResponseEntity<>(currencyDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("Currency not found: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CurrencyDTO>> getAllCurrencies() {
        try {
            List<CurrencyDTO> currencies = currencyService.getAllCurrencies();
            return new ResponseEntity<>(currencies, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyDTO> updateCurrency(@PathVariable Long id, @RequestBody CurrencyDTO currencyDTO) {
        try {
            CurrencyDTO updatedCurrency = currencyService.updateCurrency(id, currencyDTO);
            return new ResponseEntity<>(updatedCurrency, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("Currency not found: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) {
        try {
            currencyService.deleteCurrency(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            log.error("Currency not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
