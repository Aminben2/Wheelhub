package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.currencyDtos.CurrencyDto;
import com.WheelHub.WheelHub.entity.Currency;
import org.springframework.stereotype.Component;


@Component
public class CurrencyMapper {

    public static CurrencyDto entityToDTO(Currency currency) {
        return CurrencyDto.builder()
                .currencyCode(currency.getCurrencyCode())
                .currencyName(currency.getCurrencyName())
                .exchangeRate(currency.getExchangeRate())
                .build();
    }

    public static Currency dtoToEntity(CurrencyDto currencyDTO) {
        return Currency.builder()
                .currencyCode(currencyDTO.getCurrencyCode())
                .currencyName(currencyDTO.getCurrencyName())
                .exchangeRate(currencyDTO.getExchangeRate())
                .build();
    }
}
