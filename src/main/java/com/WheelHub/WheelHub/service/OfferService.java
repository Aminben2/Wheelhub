package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.offerDtos.OfferDto;
import java.util.List;

public interface OfferService {
  OfferDto save(OfferDto offerDto);

  OfferDto update(Long id, OfferDto offerDto);

  OfferDto findById(Long id);

  List<OfferDto> findAll();

  void deleteById(Long id);
}
