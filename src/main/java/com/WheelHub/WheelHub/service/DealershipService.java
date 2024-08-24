package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.DealershipDTO;

import java.util.List;

public interface DealershipService {

    DealershipDTO createDealership(DealershipDTO dealershipDTO);

    DealershipDTO getDealershipById(Long id);

    List<DealershipDTO> getAllDealerships();

    DealershipDTO updateDealership(Long id, DealershipDTO dealershipDTO);

    void deleteDealership(Long id);
}
