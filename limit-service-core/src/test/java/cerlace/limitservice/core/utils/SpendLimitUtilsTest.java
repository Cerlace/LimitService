package cerlace.limitservice.core.utils;

import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.enums.ExpenseCategory;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static cerlace.limitservice.core.MockConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class SpendLimitUtilsTest {

    @Test
    void getBaseSpendLimitTest() {
        SpendLimit actual = SpendLimitUtils.getBaseSpendLimit(ExpenseCategory.SERVICE);

        assertEquals(actual.getExpenseCategory(), ExpenseCategory.SERVICE);
        assertEquals(0, actual.getUsdSum().compareTo(Constants.BASE_LIMIT));
    }

    @Test
    void isCurrentMonthLimitTest() {
        assertTrue(SpendLimitUtils.isCurrentMonthLimit(
                SpendLimit.builder()
                        .datetime(OffsetDateTime.now())
                        .build()));
    }

    @Test
    void isLimitExceededTest() {
        assertTrue(SpendLimitUtils.isLimitExceeded(
                TEST_TRANSACTIONS_SUM,
                SpendLimit.builder()
                        .usdSum(TEST_LIMIT)
                        .build()));
    }
}