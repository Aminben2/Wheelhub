package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.paymentDtos.PaymentDto;

import java.util.List;

public interface PaymentService {

    PaymentDto createPayment(PaymentDto paymentDTO);

    PaymentDto getPaymentById(Long id);

    List<PaymentDto> getAllPayments();

    PaymentDto updatePayment(Long id, PaymentDto paymentDTO);

    void deletePayment(Long id);
}
