package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.dealershipDtos.DealershipDto;

import java.util.List;

public interface DealershipService {

    DealershipDto createDealership(DealershipDto dealershipDTO);

    DealershipDto getDealershipById(Long id);

    List<DealershipDto> getAllDealerships();

    DealershipDto updateDealership(Long id, DealershipDto dealershipDTO);

    void deleteDealership(Long id);
}
