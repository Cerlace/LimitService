package cerlace.limitservice.core.service;

import cerlace.limitservice.persistence.entity.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Сервис для работы с курсами валют.
 * <p>
 * Предоставляет методы для конвертации валют и работы с курсами обмена.
 */
public interface ExchangeRateService {

    /**
     * Конвертирует сумму из указанной валюты в USD по курсу на заданную дату.
     *
     * @param currencyShortname код валюты
     * @param sum               сумма для конвертации
     * @param date              дата курса обмена
     * @return сумма в USD
     */
    BigDecimal convertCurrencyToUsd(String currencyShortname, BigDecimal sum, LocalDate date);

    /**
     * Получает курс обмена для указанной валюты на заданную дату.
     *
     * @param currencyShortname код валюты
     * @param date              дата курса обмена
     * @return информация о курсе обмена ({@link ExchangeRate})
     */
    ExchangeRate getExchangeRate(String currencyShortname, LocalDate date);

    /**
     * Получает и сохраняет курс обмена из внешнего источника.
     *
     * @param currencyShortname код валюты
     * @param date              дата курса обмена
     * @return сохраненная информация о курсе ({@link ExchangeRate})
     */
    ExchangeRate fetchAndSaveExchangeRate(String currencyShortname, LocalDate date);
}