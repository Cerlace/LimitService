package cerlace.limitservice.core.service;

import cerlace.limitservice.persistence.entity.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeRateService {
    BigDecimal convertCurrencyToUsd(String currencyShortname, BigDecimal sum, LocalDate date);

    ExchangeRate getExchangeRate(String currencyShortname, LocalDate date);

    ExchangeRate fetchAndSaveExchangeRate(String currencyShortname, LocalDate date);
}
