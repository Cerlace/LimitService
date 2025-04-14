package cerlace.limitservice.core.utils;

import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.enums.ExpenseCategory;

public class SpendLimitUtils {
    public static SpendLimit getBaseSpendLimit(ExpenseCategory expenseCategory) {
        return SpendLimit.builder()
                .usdSum(Constants.BASE_LIMIT)
                .expenseCategory(expenseCategory)
                .build();
    }
}
