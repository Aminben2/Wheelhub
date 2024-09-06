package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.localizationDtos.LocalizationDto;
import com.WheelHub.WheelHub.entity.Localization;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Language;
import com.WheelHub.WheelHub.entity.Currency;
import org.springframework.stereotype.Component;


@Component
public class LocalizationMapper {

    public static LocalizationDto entityToDTO(Localization localization) {
        return LocalizationDto.builder()
                .userId(localization.getUser() != null ? localization.getUser().getId() : null)
                .languageId(localization.getLanguage() != null ? localization.getLanguage().getId() : null)
                .currencyId(localization.getCurrency() != null ? localization.getCurrency().getId() : null)
                .build();
    }

    public static Localization dtoToEntity(LocalizationDto localizationDTO) {
        Localization localization = Localization.builder()
                .build();

        if (localizationDTO.getUserId() != null) {
            User user = new User();
            user.setId(localizationDTO.getUserId());
            localization.setUser(user);
        }
        if (localizationDTO.getLanguageId() != null) {
            Language language = new Language();
            language.setId(localizationDTO.getLanguageId());
            localization.setLanguage(language);
        }
        if (localizationDTO.getCurrencyId() != null) {
            Currency currency = new Currency();
            currency.setId(localizationDTO.getCurrencyId());
            localization.setCurrency(currency);
        }

        return localization;
    }
}
