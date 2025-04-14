package cerlace.limitservice.core.utils;

import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.enums.ExpenseCategory;

import java.math.BigDecimal;

public class SpendLimitUtils {
    public static SpendLimit getBaseSpendLimit(ExpenseCategory expenseCategory) {
        return SpendLimit.builder()
                .usdSum(Constants.BASE_LIMIT)
                .expenseCategory(expenseCategory)
                .build();
    }

    public static boolean isCurrentMonthLimit(SpendLimit limit) {
        return DateTimeUtils.isCurrentMonth(limit.getDatetime());
    }

    public static boolean isLimitExceeded(BigDecimal transactionsSum, SpendLimit limit) {
        return transactionsSum.compareTo(limit.getUsdSum()) > 0;
    }
}
