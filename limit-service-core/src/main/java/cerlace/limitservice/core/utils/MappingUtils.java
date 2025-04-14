package cerlace.limitservice.core.utils;


public class MappingUtils {

    public static String currencyShortnameToPair(String currencyShortname) {
        return currencyShortname + Constants.SEPARATOR + Constants.USD_SHORTNAME;
    }

    public static String currencyPairToShortname(String currencyPair) {
        return currencyPair.split(Constants.SEPARATOR)[0];
    }
}
