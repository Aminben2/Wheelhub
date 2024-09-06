package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.InquiryDtos.InquiryDto;
import com.WheelHub.WheelHub.service.impl.InquiryServiceImpl;
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
@RequestMapping("/api/inquiries")
@RequiredArgsConstructor
@Validated
public class InquiryController {

    private final InquiryServiceImpl inquiryService;

    @PostMapping("/")
    public ResponseEntity<InquiryDto> createInquiry(@Valid @RequestBody InquiryDto inquiryDTO) {
        try {
            InquiryDto createdInquiry = inquiryService.createInquiry(inquiryDTO);
            return new ResponseEntity<>(createdInquiry, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InquiryDto> getInquiryById(@PathVariable @Min(1) Long id) {
        try {
            InquiryDto inquiryDTO = inquiryService.getInquiryById(id);
            return new ResponseEntity<>(inquiryDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<InquiryDto>> getAllInquiries() {
        try {
            List<InquiryDto> inquiries = inquiryService.getAllInquiries();
            return new ResponseEntity<>(inquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InquiryDto> updateInquiry(@PathVariable @Min(1) Long id,
                                                    @Valid @RequestBody InquiryDto inquiryDTO) {
        try {
            InquiryDto updatedInquiry = inquiryService.updateInquiry(id, inquiryDTO);
            return new ResponseEntity<>(updatedInquiry, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable @Min(1) Long id) {
        try {
            inquiryService.deleteInquiry(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

