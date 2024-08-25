package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.LanguageDTO;
import com.WheelHub.WheelHub.entity.Language;
import org.springframework.stereotype.Component;


@Component
public class LanguageMapper {

    public static LanguageDTO entityToDTO(Language language) {
        return LanguageDTO.builder()
                .id(language.getId())
                .languageCode(language.getLanguageCode())
                .languageName(language.getLanguageName())
                .build();
    }

    public static Language dtoToEntity(LanguageDTO languageDTO) {
        return Language.builder()
                .id(languageDTO.getId())
                .languageCode(languageDTO.getLanguageCode())
                .languageName(languageDTO.getLanguageName())
                .build();
    }
}
