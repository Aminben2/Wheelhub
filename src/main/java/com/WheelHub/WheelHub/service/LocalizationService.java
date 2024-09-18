package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.localizationDtos.LocalizationDto;
import java.util.List;

public interface LocalizationService {

  LocalizationDto createLocalization(LocalizationDto localizationDTO);

  LocalizationDto getLocalizationById(Long id);

  List<LocalizationDto> getAllLocalizations();

  LocalizationDto updateLocalization(Long id, LocalizationDto localizationDTO);

  void deleteLocalization(Long id);
}
