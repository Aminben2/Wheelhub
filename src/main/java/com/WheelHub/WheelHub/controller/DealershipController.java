package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.dealershipDtos.DealershipDto;
import com.WheelHub.WheelHub.service.impl.DealershipServiceImpl;
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
@RequestMapping("/api/dealerships")
@RequiredArgsConstructor
@Validated
public class DealershipController {

  private final DealershipServiceImpl dealershipService;

  @PostMapping("/")
  public ResponseEntity<DealershipDto> createDealership(
      @Valid @RequestBody DealershipDto dealershipDTO) {
    DealershipDto createdDealership = dealershipService.createDealership(dealershipDTO);
    return new ResponseEntity<>(createdDealership, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DealershipDto> getDealershipById(@PathVariable @Min(1) Long id) {
    DealershipDto dealershipDTO = dealershipService.getDealershipById(id);
    return new ResponseEntity<>(dealershipDTO, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<DealershipDto>> getAllDealerships() {
    List<DealershipDto> dealerships = dealershipService.getAllDealerships();
    return new ResponseEntity<>(dealerships, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DealershipDto> updateDealership(
      @PathVariable @Min(1) Long id, @Valid @RequestBody DealershipDto dealershipDTO) {
    DealershipDto updatedDealership = dealershipService.updateDealership(id, dealershipDTO);
    return new ResponseEntity<>(updatedDealership, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDealership(@PathVariable @Min(1) Long id) {
    dealershipService.deleteDealership(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
