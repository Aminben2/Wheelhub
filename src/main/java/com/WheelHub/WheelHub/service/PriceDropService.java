package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.PriceDropDTO;

import java.util.List;

public interface PriceDropService {

    PriceDropDTO createPriceDrop(PriceDropDTO priceDropDTO);

    PriceDropDTO getPriceDropById(Long id);

    List<PriceDropDTO> getAllPriceDrops();

    PriceDropDTO updatePriceDrop(Long id, PriceDropDTO priceDropDTO);

    void deletePriceDrop(Long id);
}
