package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.InquiryDtos.InquiryDto;
import com.WheelHub.WheelHub.service.impl.InquiryServiceImpl;
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
@RequestMapping("/api/inquiries")
@RequiredArgsConstructor
@Validated
public class InquiryController {

    private final InquiryServiceImpl inquiryService;

    @PostMapping("/")
    public ResponseEntity<InquiryDto> createInquiry(@Valid @RequestBody InquiryDto inquiryDTO) {

        InquiryDto createdInquiry = inquiryService.createInquiry(inquiryDTO);
        return new ResponseEntity<>(createdInquiry, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<InquiryDto> getInquiryById(@PathVariable @Min(1) Long id) {

        InquiryDto inquiryDTO = inquiryService.getInquiryById(id);
        return new ResponseEntity<>(inquiryDTO, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<InquiryDto>> getAllInquiries() {
        List<InquiryDto> inquiries = inquiryService.getAllInquiries();
        return new ResponseEntity<>(inquiries, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InquiryDto> updateInquiry(@PathVariable @Min(1) Long id,
                                                    @Valid @RequestBody InquiryDto inquiryDTO) {
        InquiryDto updatedInquiry = inquiryService.updateInquiry(id, inquiryDTO);
        return new ResponseEntity<>(updatedInquiry, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable @Min(1) Long id) {
        inquiryService.deleteInquiry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

