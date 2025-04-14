package cerlace.limitservice.core.utils;

import java.math.BigDecimal;

public class CurrencyConvertUtils {

    public static boolean isUsd(String currencyShortname) {
        return currencyShortname.equals(Constants.USD_SHORTNAME);
    }

    public static BigDecimal convert(BigDecimal sum, BigDecimal exchangeRate) {
        return sum.multiply(exchangeRate);
    }
}
