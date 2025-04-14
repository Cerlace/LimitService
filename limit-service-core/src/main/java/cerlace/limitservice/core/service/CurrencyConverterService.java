package cerlace.limitservice.core.service;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CurrencyConverterService {
    BigDecimal convertCurrencyToUsd(String currencyShortname,
                                    BigDecimal sum,
                                    LocalDate date);
}
