package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.CurrencyDTO;
import com.WheelHub.WheelHub.entity.Currency;
import org.springframework.stereotype.Component;


@Component
public class CurrencyMapper {

    public static CurrencyDTO entityToDTO(Currency currency) {
        return CurrencyDTO.builder()
                .id(currency.getId())
                .currencyCode(currency.getCurrencyCode())
                .currencyName(currency.getCurrencyName())
                .exchangeRate(currency.getExchangeRate())
                .build();
    }

    public static Currency dtoToEntity(CurrencyDTO currencyDTO) {
        return Currency.builder()
                .id(currencyDTO.getId())
                .currencyCode(currencyDTO.getCurrencyCode())
                .currencyName(currencyDTO.getCurrencyName())
                .exchangeRate(currencyDTO.getExchangeRate())
                .build();
    }
}
