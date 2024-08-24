package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.InquiryDTO;
import com.WheelHub.WheelHub.entity.Inquiry;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.mapper.InquiryMapper;
import com.WheelHub.WheelHub.repository.InquiryRepository;
import com.WheelHub.WheelHub.repository.UserRepository;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.InquiryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public InquiryDTO createInquiry(InquiryDTO inquiryDTO) {
        User user = userRepository.findById(inquiryDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + inquiryDTO.getUserId()));
        Vehicle vehicle = vehicleRepository.findById(inquiryDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + inquiryDTO.getVehicleId()));

        Inquiry inquiry = InquiryMapper.dtoToEntity(inquiryDTO);
        inquiry.setUser(user);
        inquiry.setVehicle(vehicle);

        inquiry = inquiryRepository.save(inquiry);
        return InquiryMapper.entityToDTO(inquiry);
    }

    @Override
    public InquiryDTO getInquiryById(Long id) {
        return inquiryRepository.findById(id)
                .map(InquiryMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Inquiry not found for id:" + id));
    }

    @Override
    public List<InquiryDTO> getAllInquiries() {
        return inquiryRepository.findAll().stream()
                .map(InquiryMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InquiryDTO updateInquiry(Long id, InquiryDTO inquiryDTO) {
        Inquiry existingInquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inquiry not found for id:" + id));

        User user = userRepository.findById(inquiryDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + inquiryDTO.getUserId()));
        Vehicle vehicle = vehicleRepository.findById(inquiryDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + inquiryDTO.getVehicleId()));

        existingInquiry.setUser(user);
        existingInquiry.setVehicle(vehicle);
        existingInquiry.setMessage(inquiryDTO.getMessage());
        existingInquiry.setCreatedAt(inquiryDTO.getCreatedAt());

        Inquiry updatedInquiry = inquiryRepository.save(existingInquiry);
        return InquiryMapper.entityToDTO(updatedInquiry);
    }

    @Override
    public void deleteInquiry(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inquiry not found for id:" + id));
        inquiryRepository.delete(inquiry);
    }
}
