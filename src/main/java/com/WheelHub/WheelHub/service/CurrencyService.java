package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.currencyDtos.CurrencyDto;

import java.util.List;

public interface CurrencyService {

    CurrencyDto createCurrency(CurrencyDto currencyDTO);

    CurrencyDto getCurrencyById(Long id);

    List<CurrencyDto> getAllCurrencies();

    CurrencyDto updateCurrency(Long id, CurrencyDto currencyDTO);

    void deleteCurrency(Long id);
}
