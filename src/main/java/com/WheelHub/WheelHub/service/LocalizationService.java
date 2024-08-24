package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.LocalizationDTO;

import java.util.List;

public interface LocalizationService {

    LocalizationDTO createLocalization(LocalizationDTO localizationDTO);

    LocalizationDTO getLocalizationById(Long id);

    List<LocalizationDTO> getAllLocalizations();

    LocalizationDTO updateLocalization(Long id, LocalizationDTO localizationDTO);

    void deleteLocalization(Long id);
}
