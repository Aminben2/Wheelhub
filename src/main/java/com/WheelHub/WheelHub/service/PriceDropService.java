package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropDto;

import java.util.List;

public interface PriceDropService {

    PriceDropDto createPriceDrop(PriceDropDto priceDropDTO);

    PriceDropDto getPriceDropById(Long id);

    List<PriceDropDto> getAllPriceDrops();

    PriceDropDto updatePriceDrop(Long id, PriceDropDto priceDropDTO);

    void deletePriceDrop(Long id);
}
