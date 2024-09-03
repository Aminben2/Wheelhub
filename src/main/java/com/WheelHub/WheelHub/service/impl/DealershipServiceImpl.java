package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.dealershipDtos.DealershipDto;
import com.WheelHub.WheelHub.entity.Dealership;
import com.WheelHub.WheelHub.mapper.DealershipMapper;
import com.WheelHub.WheelHub.repository.DealershipRepository;
import com.WheelHub.WheelHub.service.DealershipService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DealershipServiceImpl implements DealershipService {

    private final DealershipRepository dealershipRepository;

    @Override
    public DealershipDto createDealership(DealershipDto dealershipDTO) {
        Dealership dealership = DealershipMapper.dtoToEntity(dealershipDTO);
        dealership = dealershipRepository.save(dealership);
        return DealershipMapper.entityToDTO(dealership);
    }

    @Override
    public DealershipDto getDealershipById(Long id) {
        return dealershipRepository.findById(id)
                .map(DealershipMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Dealership not found for id:" + id));
    }

    @Override
    public List<DealershipDto> getAllDealerships() {
        return dealershipRepository.findAll().stream()
                .map(DealershipMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DealershipDto updateDealership(Long id, DealershipDto dealershipDTO) {
        Dealership existingDealership = dealershipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dealership not found for id:" + id));

        existingDealership.setName(dealershipDTO.getName());
        existingDealership.setLocation(dealershipDTO.getLocation());
        existingDealership.setContactInfo(dealershipDTO.getContactInfo());

        Dealership updatedDealership = dealershipRepository.save(existingDealership);
        return DealershipMapper.entityToDTO(updatedDealership);
    }

    @Override
    public void deleteDealership(Long id) {
        Dealership dealership = dealershipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dealership not found for id:" + id));
        dealershipRepository.delete(dealership);
    }
}
