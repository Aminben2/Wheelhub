package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropDto;
import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropResponseDto;
import com.WheelHub.WheelHub.entity.PriceDrop;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.mapper.PriceDropMapper;
import com.WheelHub.WheelHub.repository.PriceDropRepository;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.PriceDropService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceDropServiceImpl implements PriceDropService {

    private final PriceDropRepository priceDropRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public PriceDropResponseDto createPriceDrop(PriceDropDto priceDropDTO) {
        Vehicle vehicle = vehicleRepository.findById(priceDropDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + priceDropDTO.getVehicleId()));

        PriceDrop priceDrop = PriceDropMapper.dtoToEntity(priceDropDTO);
        priceDrop.setVehicle(vehicle);

        priceDrop = priceDropRepository.save(priceDrop);
        return PriceDropMapper.entityToResponseDTO(priceDrop);
    }

    @Override
    public PriceDropResponseDto getPriceDropById(Long id) {
        return priceDropRepository.findById(id)
                .map(PriceDropMapper::entityToResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("PriceDrop not found for id:" + id));
    }

    @Override
    public PriceDropResponseDto getLatestPriceDropByVehicleId(Long id) {
        return priceDropRepository.findFirstByVehicleIdOrderByDroppedAtDesc(id)
                .map(PriceDropMapper::entityToResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("PriceDrop not found for vehicle id:" + id));
    }

    @Override
    public List<PriceDropResponseDto> getAllPriceDrops() {
        return priceDropRepository.findAll().stream()
                .map(PriceDropMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PriceDropResponseDto updatePriceDrop(Long id, PriceDropDto priceDropDTO) {
        PriceDrop existingPriceDrop = priceDropRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PriceDrop not found for id:" + id));

        Vehicle vehicle = vehicleRepository.findById(priceDropDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + priceDropDTO.getVehicleId()));

        existingPriceDrop.setVehicle(vehicle);
        existingPriceDrop.setOldPrice(priceDropDTO.getOldPrice());
        existingPriceDrop.setNewPrice(priceDropDTO.getNewPrice());
        existingPriceDrop.setDroppedAt(priceDropDTO.getDroppedAt());

        PriceDrop updatedPriceDrop = priceDropRepository.save(existingPriceDrop);
        return PriceDropMapper.entityToResponseDTO(updatedPriceDrop);
    }

    @Override
    public void deletePriceDrop(Long id) {
        PriceDrop priceDrop = priceDropRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PriceDrop not found for id:" + id));
        priceDropRepository.delete(priceDrop);
    }
}
