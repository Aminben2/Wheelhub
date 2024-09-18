package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.languageDtos.LanguageDto;
import com.WheelHub.WheelHub.entity.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {

  public static LanguageDto entityToDTO(Language language) {
    return LanguageDto.builder()
        .languageCode(language.getLanguageCode())
        .languageName(language.getLanguageName())
        .build();
  }

  public static Language dtoToEntity(LanguageDto languageDTO) {
    return Language.builder()
        .languageCode(languageDTO.getLanguageCode())
        .languageName(languageDTO.getLanguageName())
        .build();
  }
}
