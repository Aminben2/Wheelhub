package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropDto;
import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropResponseDto;

import java.util.List;

public interface PriceDropService {

    PriceDropResponseDto createPriceDrop(PriceDropDto priceDropDTO);

    PriceDropResponseDto getPriceDropById(Long id);

    PriceDropResponseDto getLatestPriceDropByVehicleId(Long id);

    List<PriceDropResponseDto> getAllPriceDrops();

    PriceDropResponseDto updatePriceDrop(Long id, PriceDropDto priceDropDTO);

    void deletePriceDrop(Long id);
}
