package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.InquiryDTO;
import com.WheelHub.WheelHub.service.impl.InquiryServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/inquiries")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryServiceImpl inquiryService;

    @PostMapping("/")
    public ResponseEntity<InquiryDTO> createInquiry(@RequestBody InquiryDTO inquiryDTO) {
        try {
            InquiryDTO createdInquiry = inquiryService.createInquiry(inquiryDTO);
            return new ResponseEntity<>(createdInquiry, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InquiryDTO> getInquiryById(@PathVariable Long id) {
        try {
            InquiryDTO inquiryDTO = inquiryService.getInquiryById(id);
            return new ResponseEntity<>(inquiryDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<InquiryDTO>> getAllInquiries() {
        try {
            List<InquiryDTO> inquiries = inquiryService.getAllInquiries();
            return new ResponseEntity<>(inquiries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InquiryDTO> updateInquiry(@PathVariable Long id, @RequestBody InquiryDTO inquiryDTO) {
        try {
            InquiryDTO updatedInquiry = inquiryService.updateInquiry(id, inquiryDTO);
            return new ResponseEntity<>(updatedInquiry, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable Long id) {
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

