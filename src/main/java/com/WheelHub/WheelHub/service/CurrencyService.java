package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.CurrencyDTO;

import java.util.List;

public interface CurrencyService {

    CurrencyDTO createCurrency(CurrencyDTO currencyDTO);

    CurrencyDTO getCurrencyById(Long id);

    List<CurrencyDTO> getAllCurrencies();

    CurrencyDTO updateCurrency(Long id, CurrencyDTO currencyDTO);

    void deleteCurrency(Long id);
}
