package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.offerDtos.OfferDto;
import com.WheelHub.WheelHub.entity.Offer;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {

    public static OfferDto entityToDTO(Offer offer) {
        return OfferDto.builder()
                .vehicleId(offer.getVehicle() != null ? offer.getVehicle().getId() : null)
                .buyerId(offer.getBuyer() != null ? offer.getBuyer().getId() : null)
                .sellerId(offer.getSeller() != null ? offer.getSeller().getId() : null)
                .offerAmount(offer.getOfferAmount())
                .status(offer.getStatus())
                .build();
    }

    public static Offer dtoToEntity(OfferDto offerDto) {
        Offer offer = Offer.builder()
                .offerAmount(offerDto.getOfferAmount())
                .status(offerDto.getStatus())
                .build();

        if (offerDto.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(offerDto.getVehicleId());
            offer.setVehicle(vehicle);
        }
        if (offerDto.getBuyerId() != null) {
            User buyer = new User();
            buyer.setId(offerDto.getBuyerId());
            offer.setBuyer(buyer);
        }
        if (offerDto.getSellerId() != null) {
            User seller = new User();
            seller.setId(offerDto.getSellerId());
            offer.setSeller(seller);
        }

        return offer;
    }
}
