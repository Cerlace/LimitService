package cerlace.limitservice.core.utils;

import java.math.BigDecimal;

/**
 * Утилитарный класс для работы с валютами и конвертацией сумм.
 */
public class CurrencyConvertUtils {
    /**
     * Проверяет, является ли указанное краткое наименование валюты долларом США.
     *
     * @param currencyShortname краткое наименование валюты (например, "USD")
     * @return {@code true}, если валюта — доллар США, иначе {@code false}
     */
    public static boolean isUsd(String currencyShortname) {
        return currencyShortname.equals(Constants.USD_SHORTNAME);
    }

    /**
     * Конвертирует сумму по заданному курсу обмена.
     *
     * @param sum          исходная сумма
     * @param exchangeRate курс обмена
     * @return конвертированная сумма
     */
    public static BigDecimal convert(BigDecimal sum, BigDecimal exchangeRate) {
        return sum.multiply(exchangeRate);
    }
}
