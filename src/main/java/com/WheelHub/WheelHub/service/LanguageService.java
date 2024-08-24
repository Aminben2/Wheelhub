package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.LanguageDTO;

import java.util.List;

public interface LanguageService {

    LanguageDTO createLanguage(LanguageDTO languageDTO);

    LanguageDTO getLanguageById(Long id);

    List<LanguageDTO> getAllLanguages();

    LanguageDTO updateLanguage(Long id, LanguageDTO languageDTO);

    void deleteLanguage(Long id);
}
