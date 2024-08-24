package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    PaymentDTO createPayment(PaymentDTO paymentDTO);

    PaymentDTO getPaymentById(Long id);

    List<PaymentDTO> getAllPayments();

    PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO);

    void deletePayment(Long id);
}
