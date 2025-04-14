package cerlace.limitservice.core.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static cerlace.limitservice.core.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyConvertUtilsTest {

    @Test
    void isUsdTest() {
        assertTrue(CurrencyConvertUtils.isUsd(USD_SHORTNAME));
        assertFalse(CurrencyConvertUtils.isUsd(EUR_SHORTNAME));
    }

    @Test
    void convertTest() {
        BigDecimal actual = CurrencyConvertUtils.convert(TEST_SUM, TEST_RATE);
        assertEquals(0, actual.compareTo(EXPECTED_SUM));
    }
}