package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropDto;
import com.WheelHub.WheelHub.entity.PriceDrop;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.mapper.PriceDropMapper;
import com.WheelHub.WheelHub.repository.PriceDropRepository;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.PriceDropService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceDropServiceImpl implements PriceDropService {

    private final PriceDropRepository priceDropRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public PriceDropDto createPriceDrop(PriceDropDto priceDropDTO) {
        Vehicle vehicle = vehicleRepository.findById(priceDropDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + priceDropDTO.getVehicleId()));

        PriceDrop priceDrop = PriceDropMapper.dtoToEntity(priceDropDTO);
        priceDrop.setVehicle(vehicle);

        priceDrop = priceDropRepository.save(priceDrop);
        return PriceDropMapper.entityToDTO(priceDrop);
    }

    @Override
    public PriceDropDto getPriceDropById(Long id) {
        return priceDropRepository.findById(id)
                .map(PriceDropMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("PriceDrop not found for id:" + id));
    }

    @Override
    public List<PriceDropDto> getAllPriceDrops() {
        return priceDropRepository.findAll().stream()
                .map(PriceDropMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PriceDropDto updatePriceDrop(Long id, PriceDropDto priceDropDTO) {
        PriceDrop existingPriceDrop = priceDropRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PriceDrop not found for id:" + id));

        Vehicle vehicle = vehicleRepository.findById(priceDropDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + priceDropDTO.getVehicleId()));

        existingPriceDrop.setVehicle(vehicle);
        existingPriceDrop.setOldPrice(priceDropDTO.getOldPrice());
        existingPriceDrop.setNewPrice(priceDropDTO.getNewPrice());
        existingPriceDrop.setDroppedAt(priceDropDTO.getDroppedAt());

        PriceDrop updatedPriceDrop = priceDropRepository.save(existingPriceDrop);
        return PriceDropMapper.entityToDTO(updatedPriceDrop);
    }

    @Override
    public void deletePriceDrop(Long id) {
        PriceDrop priceDrop = priceDropRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PriceDrop not found for id:" + id));
        priceDropRepository.delete(priceDrop);
    }
}
