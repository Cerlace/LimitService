package cerlace.limitservice.core.utils;

/**
 * Утилитарный класс для преобразования валютных пар и кратких наименований валют.
 */
public class MappingUtils {

    /**
     * Преобразует краткое наименование валюты в валютную пару с долларом США.
     *
     * @param currencyShortname краткое наименование валюты (например, "EUR")
     * @return валютная пара в формате "EUR/USD"
     */
    public static String currencyShortnameToPair(String currencyShortname) {
        return currencyShortname + Constants.SEPARATOR + Constants.USD_SHORTNAME;
    }

    /**
     * Извлекает краткое наименование валюты из валютной пары.
     *
     * @param currencyPair валютная пара (например, "EUR/USD")
     * @return краткое наименование валюты (например, "EUR")
     */
    public static String currencyPairToShortname(String currencyPair) {
        return currencyPair.split(Constants.SEPARATOR)[0];
    }
}
