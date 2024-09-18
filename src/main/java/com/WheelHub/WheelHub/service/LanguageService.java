package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.languageDtos.LanguageDto;
import java.util.List;

public interface LanguageService {

  LanguageDto createLanguage(LanguageDto languageDTO);

  LanguageDto getLanguageById(Long id);

  List<LanguageDto> getAllLanguages();

  LanguageDto updateLanguage(Long id, LanguageDto languageDTO);

  void deleteLanguage(Long id);
}
