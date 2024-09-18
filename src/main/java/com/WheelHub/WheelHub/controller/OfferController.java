package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.offerDtos.OfferDto;
import com.WheelHub.WheelHub.service.OfferService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
@Validated
public class OfferController {

  private final OfferService offerService;

  @PostMapping("/")
  public ResponseEntity<OfferDto> createOffer(@Valid @RequestBody OfferDto offerDto) {

    OfferDto createdOffer = offerService.save(offerDto);
    return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OfferDto> getOfferById(@PathVariable @Min(1) Long id) {

    OfferDto offerDto = offerService.findById(id);
    return new ResponseEntity<>(offerDto, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<OfferDto>> getAllOffers() {
    List<OfferDto> offers = offerService.findAll();
    return new ResponseEntity<>(offers, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<OfferDto> updateOffer(
      @PathVariable @Min(1) Long id, @Valid @RequestBody OfferDto offerDto) {

    OfferDto updatedOffer = offerService.update(id, offerDto);
    return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOffer(@PathVariable @Min(1) Long id) {

    offerService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
