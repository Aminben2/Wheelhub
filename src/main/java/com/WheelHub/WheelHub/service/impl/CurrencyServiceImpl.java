package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.currencyDtos.CurrencyDto;
import com.WheelHub.WheelHub.entity.Currency;
import com.WheelHub.WheelHub.mapper.CurrencyMapper;
import com.WheelHub.WheelHub.repository.CurrencyRepository;
import com.WheelHub.WheelHub.service.CurrencyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public CurrencyDto createCurrency(CurrencyDto currencyDTO) {
        Currency currency = CurrencyMapper.dtoToEntity(currencyDTO);
        currency = currencyRepository.save(currency);
        return CurrencyMapper.entityToDTO(currency);
    }

    @Override
    public CurrencyDto getCurrencyById(Long id) {
        return currencyRepository.findById(id)
                .map(CurrencyMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Currency not found for id:" + id));
    }

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        return currencyRepository.findAll().stream()
                .map(CurrencyMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CurrencyDto updateCurrency(Long id, CurrencyDto currencyDTO) {
        Currency existingCurrency = currencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Currency not found for id:" + id));

        existingCurrency.setCurrencyCode(currencyDTO.getCurrencyCode());
        existingCurrency.setCurrencyName(currencyDTO.getCurrencyName());
        existingCurrency.setExchangeRate(currencyDTO.getExchangeRate());

        Currency updatedCurrency = currencyRepository.save(existingCurrency);
        return CurrencyMapper.entityToDTO(updatedCurrency);
    }

    @Override
    public void deleteCurrency(Long id) {
        Currency currency = currencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Currency not found for id:" + id));
        currencyRepository.delete(currency);
    }
}
