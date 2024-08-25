package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.PaymentDTO;
import com.WheelHub.WheelHub.entity.Payment;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class PaymentMapper {

    public static PaymentDTO entityToDTO(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .userId(payment.getUser() != null ? payment.getUser().getId() : null)
                .vehicleId(payment.getVehicle() != null ? payment.getVehicle().getId() : null)
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAt())
                .build();
    }

    public static Payment dtoToEntity(PaymentDTO paymentDTO) {
        Payment payment = Payment.builder()
                .id(paymentDTO.getId())
                .amount(paymentDTO.getAmount())
                .paymentMethod(paymentDTO.getPaymentMethod())
                .status(paymentDTO.getStatus())
                .createdAt(paymentDTO.getCreatedAt())
                .build();

        if (paymentDTO.getUserId() != null) {
            User user = new User();
            user.setId(paymentDTO.getUserId());
            payment.setUser(user);
        }
        if (paymentDTO.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(paymentDTO.getVehicleId());
            payment.setVehicle(vehicle);
        }

        return payment;
    }
}
