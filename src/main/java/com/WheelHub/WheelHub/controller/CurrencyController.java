package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.currencyDtos.CurrencyDto;
import com.WheelHub.WheelHub.service.impl.CurrencyServiceImpl;
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
        CurrencyDto createdCurrency = currencyService.createCurrency(currencyDTO);
        return new ResponseEntity<>(createdCurrency, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDto> getCurrencyById(@PathVariable @Min(1) Long id) {
        CurrencyDto currencyDTO = currencyService.getCurrencyById(id);
        return new ResponseEntity<>(currencyDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CurrencyDto>> getAllCurrencies() {
        List<CurrencyDto> currencies = currencyService.getAllCurrencies();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyDto> updateCurrency(@PathVariable @Min(1) Long id,@Valid @RequestBody CurrencyDto currencyDTO) {
        CurrencyDto updatedCurrency = currencyService.updateCurrency(id, currencyDTO);
        return new ResponseEntity<>(updatedCurrency, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable @Min(1) Long id) {
        currencyService.deleteCurrency(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
