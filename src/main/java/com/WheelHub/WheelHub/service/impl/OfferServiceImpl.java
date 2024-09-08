package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.offerDtos.OfferDto;
import com.WheelHub.WheelHub.entity.Offer;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.mapper.OfferMapper;
import com.WheelHub.WheelHub.repository.OfferRepository;
import com.WheelHub.WheelHub.service.OfferService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public OfferDto save(OfferDto offerDto) {
        Offer offer = OfferMapper.dtoToEntity(offerDto);
        offer = offerRepository.save(offer);
        return OfferMapper.entityToDTO(offer);
    }

    @Override
    public OfferDto update(Long id, OfferDto offerDto) {
        Offer existingOffer = offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found for id: " + id));

        if (offerDto.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(offerDto.getVehicleId());
            existingOffer.setVehicle(vehicle);
        }
        if (offerDto.getBuyerId() != null) {
            User buyer = new User();
            buyer.setId(offerDto.getBuyerId());
            existingOffer.setBuyer(buyer);
        }
        if (offerDto.getSellerId() != null) {
            User seller = new User();
            seller.setId(offerDto.getSellerId());
            existingOffer.setSeller(seller);
        }
        existingOffer.setOfferAmount(offerDto.getOfferAmount());
        existingOffer.setStatus(offerDto.getStatus());

        Offer updatedOffer = offerRepository.save(existingOffer);
        return OfferMapper.entityToDTO(updatedOffer);
    }


    @Override
    public OfferDto findById(Long id) {
        return offerRepository.findById(id)
                .map(OfferMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found for id: " + id));
    }

    @Override
    public List<OfferDto> findAll() {
        return offerRepository.findAll().stream()
                .map(OfferMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found for id: " + id));
        offerRepository.delete(offer);
    }
}
