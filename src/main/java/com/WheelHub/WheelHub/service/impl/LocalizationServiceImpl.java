package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.localizationDtos.LocalizationDto;
import com.WheelHub.WheelHub.entity.Localization;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Language;
import com.WheelHub.WheelHub.entity.Currency;
import com.WheelHub.WheelHub.mapper.LocalizationMapper;
import com.WheelHub.WheelHub.repository.LocalizationRepository;
import com.WheelHub.WheelHub.repository.UserRepository;
import com.WheelHub.WheelHub.repository.LanguageRepository;
import com.WheelHub.WheelHub.repository.CurrencyRepository;
import com.WheelHub.WheelHub.service.LocalizationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocalizationServiceImpl implements LocalizationService {

    private final LocalizationRepository localizationRepository;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    public LocalizationDto createLocalization(LocalizationDto localizationDTO) {
        User user = userRepository.findById(localizationDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + localizationDTO.getUserId()));
        Language language = languageRepository.findById(localizationDTO.getLanguageId())
                .orElseThrow(() -> new EntityNotFoundException("Language not found for id:" + localizationDTO.getLanguageId()));
        Currency currency = currencyRepository.findById(localizationDTO.getCurrencyId())
                .orElseThrow(() -> new EntityNotFoundException("Currency not found for id:" + localizationDTO.getCurrencyId()));

        Localization localization = LocalizationMapper.dtoToEntity(localizationDTO);
        localization.setUser(user);
        localization.setLanguage(language);
        localization.setCurrency(currency);

        localization = localizationRepository.save(localization);
        return LocalizationMapper.entityToDTO(localization);
    }

    @Override
    public LocalizationDto getLocalizationById(Long id) {
        return localizationRepository.findById(id)
                .map(LocalizationMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Localization not found for id:" + id));
    }

    @Override
    public List<LocalizationDto> getAllLocalizations() {
        return localizationRepository.findAll().stream()
                .map(LocalizationMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LocalizationDto updateLocalization(Long id, LocalizationDto localizationDTO) {
        Localization existingLocalization = localizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Localization not found for id:" + id));

        User user = userRepository.findById(localizationDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + localizationDTO.getUserId()));
        Language language = languageRepository.findById(localizationDTO.getLanguageId())
                .orElseThrow(() -> new EntityNotFoundException("Language not found for id:" + localizationDTO.getLanguageId()));
        Currency currency = currencyRepository.findById(localizationDTO.getCurrencyId())
                .orElseThrow(() -> new EntityNotFoundException("Currency not found for id:" + localizationDTO.getCurrencyId()));

        existingLocalization.setUser(user);
        existingLocalization.setLanguage(language);
        existingLocalization.setCurrency(currency);

        Localization updatedLocalization = localizationRepository.save(existingLocalization);
        return LocalizationMapper.entityToDTO(updatedLocalization);
    }

    @Override
    public void deleteLocalization(Long id) {
        Localization localization = localizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Localization not found for id:" + id));
        localizationRepository.delete(localization);
    }
}
