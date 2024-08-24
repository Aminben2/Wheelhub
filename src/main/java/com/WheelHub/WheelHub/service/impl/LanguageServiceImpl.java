package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.LanguageDTO;
import com.WheelHub.WheelHub.entity.Language;
import com.WheelHub.WheelHub.mapper.LanguageMapper;
import com.WheelHub.WheelHub.repository.LanguageRepository;
import com.WheelHub.WheelHub.service.LanguageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public LanguageDTO createLanguage(LanguageDTO languageDTO) {
        Language language = LanguageMapper.dtoToEntity(languageDTO);
        language = languageRepository.save(language);
        return LanguageMapper.entityToDTO(language);
    }

    @Override
    public LanguageDTO getLanguageById(Long id) {
        return languageRepository.findById(id)
                .map(LanguageMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Language not found for id:" + id));
    }

    @Override
    public List<LanguageDTO> getAllLanguages() {
        return languageRepository.findAll().stream()
                .map(LanguageMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LanguageDTO updateLanguage(Long id, LanguageDTO languageDTO) {
        Language existingLanguage = languageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language not found for id:" + id));

        existingLanguage.setLanguageCode(languageDTO.getLanguageCode());
        existingLanguage.setLanguageName(languageDTO.getLanguageName());

        Language updatedLanguage = languageRepository.save(existingLanguage);
        return LanguageMapper.entityToDTO(updatedLanguage);
    }

    @Override
    public void deleteLanguage(Long id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language not found for id:" + id));
        languageRepository.delete(language);
    }
}
