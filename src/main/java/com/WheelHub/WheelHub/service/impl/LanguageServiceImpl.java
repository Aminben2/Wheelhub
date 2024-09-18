package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.languageDtos.LanguageDto;
import com.WheelHub.WheelHub.entity.Language;
import com.WheelHub.WheelHub.mapper.LanguageMapper;
import com.WheelHub.WheelHub.repository.LanguageRepository;
import com.WheelHub.WheelHub.service.LanguageService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

  private final LanguageRepository languageRepository;

  @Override
  @Transactional
  public LanguageDto createLanguage(LanguageDto languageDTO) {
    Language language = LanguageMapper.dtoToEntity(languageDTO);
    language = languageRepository.save(language);
    return LanguageMapper.entityToDTO(language);
  }

  @Override
  public LanguageDto getLanguageById(Long id) {
    return languageRepository
        .findById(id)
        .map(LanguageMapper::entityToDTO)
        .orElseThrow(() -> new EntityNotFoundException("Language not found for id:" + id));
  }

  @Override
  public List<LanguageDto> getAllLanguages() {
    return languageRepository.findAll().stream()
        .map(LanguageMapper::entityToDTO)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public LanguageDto updateLanguage(Long id, LanguageDto languageDTO) {
    Language existingLanguage =
        languageRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Language not found for id:" + id));

    existingLanguage.setLanguageCode(languageDTO.getLanguageCode());
    existingLanguage.setLanguageName(languageDTO.getLanguageName());

    Language updatedLanguage = languageRepository.save(existingLanguage);
    return LanguageMapper.entityToDTO(updatedLanguage);
  }

  @Override
  public void deleteLanguage(Long id) {
    Language language =
        languageRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Language not found for id:" + id));
    languageRepository.delete(language);
  }
}
