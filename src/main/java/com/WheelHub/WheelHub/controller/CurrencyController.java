package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.currencyDtos.CurrencyDto;
import com.WheelHub.WheelHub.service.impl.CurrencyServiceImpl;
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
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
@Validated
public class CurrencyController {

    private final CurrencyServiceImpl currencyService;

    @PostMapping("/")
    public ResponseEntity<CurrencyDto> createCurrency(@Valid @RequestBody CurrencyDto currencyDTO) {
        try {
            CurrencyDto createdCurrency = currencyService.createCurrency(currencyDTO);
            return new ResponseEntity<>(createdCurrency, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDto> getCurrencyById(@PathVariable @Min(1) Long id) {
        try {
            CurrencyDto currencyDTO = currencyService.getCurrencyById(id);
            return new ResponseEntity<>(currencyDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CurrencyDto>> getAllCurrencies() {
        try {
            List<CurrencyDto> currencies = currencyService.getAllCurrencies();
            return new ResponseEntity<>(currencies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyDto> updateCurrency(@PathVariable @Min(1) Long id,@Valid @RequestBody CurrencyDto currencyDTO) {
        try {
            CurrencyDto updatedCurrency = currencyService.updateCurrency(id, currencyDTO);
            return new ResponseEntity<>(updatedCurrency, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable @Min(1) Long id) {
        try {
            currencyService.deleteCurrency(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
