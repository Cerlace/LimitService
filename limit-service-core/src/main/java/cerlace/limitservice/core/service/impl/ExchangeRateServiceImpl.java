package cerlace.limitservice.core.service.impl;

import cerlace.limitservice.core.external.ExchangeRateApiClient;
import cerlace.limitservice.core.mapper.ExchangeRateMapper;
import cerlace.limitservice.core.service.ExchangeRateService;
import cerlace.limitservice.core.utils.Constants;
import cerlace.limitservice.core.utils.CurrencyConvertUtils;
import cerlace.limitservice.core.utils.MappingUtils;
import cerlace.limitservice.persistence.entity.ExchangeRate;
import cerlace.limitservice.persistence.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateMapper exchangeRateMapper;

    private final ExchangeRateApiClient exchangeRateApiClient;

    @Override
    public BigDecimal convertCurrencyToUsd(String currencyShortname, BigDecimal sum, LocalDate date) {
        return CurrencyConvertUtils.isUsd(currencyShortname) ? sum :
                CurrencyConvertUtils.convert(sum, getExchangeRate(currencyShortname, date).getRate());
    }

    @Override
    public ExchangeRate getExchangeRate(String currencyShortname, LocalDate date) {
        return exchangeRateRepository
                .findByCurrencyShortnameAndDate(currencyShortname, date)
                .orElse(fetchAndSaveExchangeRate(currencyShortname,date));
    }

    @Override
    public ExchangeRate fetchAndSaveExchangeRate(String currencyShortname, LocalDate date) {
        ExchangeRate requestedRate = exchangeRateMapper.toEntity(
                exchangeRateApiClient.getExchangeRate(
                        MappingUtils.currencyShortnameToPair(currencyShortname), date));
        requestedRate.setDate(date);
        return exchangeRateRepository.save(requestedRate);
    }
}
