package cerlace.limitservice.core.service;

import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.enums.ExpenseCategory;

/**
 * Сервис для работы с лимитами расходов.
 * <p>
 * Предоставляет методы для получения информации о текущих лимитах.
 */
public interface SpendLimitService {

    /**
     * Получает текущий (последний установленный) лимит для указанной категории расходов.
     *
     * @param expenseCategory категория расходов
     * @return информация о текущем лимите ({@link SpendLimit}) или, если лимит не установлен,
     * устанавливает базовый лимит.
     */
    SpendLimit getCurrentSpendLimit(ExpenseCategory expenseCategory);
}