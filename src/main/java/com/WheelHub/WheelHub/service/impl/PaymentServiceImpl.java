package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.PaymentDTO;
import com.WheelHub.WheelHub.entity.Payment;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.mapper.PaymentMapper;
import com.WheelHub.WheelHub.repository.PaymentRepository;
import com.WheelHub.WheelHub.repository.UserRepository;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        User user = userRepository.findById(paymentDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + paymentDTO.getUserId()));
        Vehicle vehicle = vehicleRepository.findById(paymentDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + paymentDTO.getVehicleId()));

        Payment payment = PaymentMapper.dtoToEntity(paymentDTO);
        payment.setUser(user);
        payment.setVehicle(vehicle);

        payment = paymentRepository.save(payment);
        return PaymentMapper.entityToDTO(payment);
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(PaymentMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found for id:" + id));
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(PaymentMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found for id:" + id));

        User user = userRepository.findById(paymentDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + paymentDTO.getUserId()));
        Vehicle vehicle = vehicleRepository.findById(paymentDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + paymentDTO.getVehicleId()));

        existingPayment.setUser(user);
        existingPayment.setVehicle(vehicle);
        existingPayment.setAmount(paymentDTO.getAmount());
        existingPayment.setPaymentMethod(paymentDTO.getPaymentMethod());
        existingPayment.setStatus(paymentDTO.getStatus());
        existingPayment.setCreatedAt(paymentDTO.getCreatedAt());

        Payment updatedPayment = paymentRepository.save(existingPayment);
        return PaymentMapper.entityToDTO(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found for id:" + id));
        paymentRepository.delete(payment);
    }
}
