package cerlace.limitservice.core.utils;

import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.enums.ExpenseCategory;

import java.math.BigDecimal;

/**
 * Утилитарный класс для работы с лимитами трат.
 */
public class SpendLimitUtils {

    /**
     * Создаёт базовый лимит трат для указанной категории расходов.
     *
     * @param expenseCategory категория расходов
     * @return объект {@link SpendLimit} с базовым лимитом
     */
    public static SpendLimit getBaseSpendLimit(ExpenseCategory expenseCategory) {
        return SpendLimit.builder()
                .usdSum(Constants.BASE_LIMIT)
                .expenseCategory(expenseCategory)
                .build();
    }

    /**
     * Проверяет, относится ли лимит к текущему месяцу.
     *
     * @param limit объект лимита трат
     * @return {@code true}, если лимит установлен на текущий месяц, иначе {@code false}
     */
    public static boolean isCurrentMonthLimit(SpendLimit limit) {
        return DateTimeUtils.isCurrentMonth(limit.getDatetime());
    }

    /**
     * Проверяет, превышена ли сумма транзакций по сравнению с лимитом.
     *
     * @param transactionsSum сумма всех транзакций
     * @param limit           лимит трат
     * @return {@code true}, если сумма транзакций превышает лимит, иначе {@code false}
     */
    public static boolean isLimitExceeded(BigDecimal transactionsSum, SpendLimit limit) {
        return transactionsSum.compareTo(limit.getUsdSum()) > 0;
    }
}
