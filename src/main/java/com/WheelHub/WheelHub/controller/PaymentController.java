package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.paymentDtos.PaymentDto;
import com.WheelHub.WheelHub.service.impl.PaymentServiceImpl;
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
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Validated
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @PostMapping("/")
    public ResponseEntity<PaymentDto> createPayment(
            @Valid @RequestBody PaymentDto paymentDTO) {
   
            PaymentDto createdPayment = paymentService.createPayment(paymentDTO);
            return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable @Min(1) Long id) {
   
            PaymentDto paymentDTO = paymentService.getPaymentById(id);
            return new ResponseEntity<>(paymentDTO, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
            List<PaymentDto> payments = paymentService.getAllPayments();
            return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable @Min(1) Long id,@Valid @RequestBody PaymentDto paymentDTO) {
   
            PaymentDto updatedPayment = paymentService.updatePayment(id, paymentDTO);
            return new ResponseEntity<>(updatedPayment, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable @Min(1) Long id) {
   
            paymentService.deletePayment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
