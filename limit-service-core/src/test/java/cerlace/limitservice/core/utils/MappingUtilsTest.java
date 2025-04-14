package cerlace.limitservice.core.utils;

import org.junit.jupiter.api.Test;

import static cerlace.limitservice.core.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class MappingUtilsTest {

    @Test
    void currencyShortnameToPairTest() {
        String actual = MappingUtils.currencyShortnameToPair(TEST_SHORTNAME);
        assertEquals(actual, TEST_PAIR);
    }

    @Test
    void currencyPairToShortnameTest() {
        String actual = MappingUtils.currencyPairToShortname(TEST_PAIR);
        assertEquals(actual, TEST_SHORTNAME);
    }
}