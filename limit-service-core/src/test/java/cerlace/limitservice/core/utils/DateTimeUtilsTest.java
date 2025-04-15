package cerlace.limitservice.core.utils;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static cerlace.limitservice.core.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTest {

    @Test
    void isCurrentMonthTest() {
        assertTrue(DateTimeUtils.isCurrentMonth(OffsetDateTime.now()));
    }

    @Test
    void getMonthStartTest() {
        OffsetDateTime actual = DateTimeUtils.getMonthStart(TEST_DATE);
        assertEquals(actual, EXPECTED_MONTH_START);
    }

    @Test
    void getMonthEndTest() {
        OffsetDateTime actual = DateTimeUtils.getMonthEnd(TEST_DATE);
        assertEquals(actual, EXPECTED_MONTH_END);
    }
}