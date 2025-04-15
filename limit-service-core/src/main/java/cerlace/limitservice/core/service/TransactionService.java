package cerlace.limitservice.core.service;

import cerlace.limitservice.core.dto.TransactionCreateRequest;
import cerlace.limitservice.core.dto.TransactionResponse;
import cerlace.limitservice.persistence.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Сервис для работы с транзакциями.
 * <p>
 * Предоставляет методы для обработки и сохранения транзакций.
 */
public interface TransactionService {

    /**
     * Сохраняет новую транзакцию в системе.
     * <p>
     * Выполняет конвертацию валюты, проверку лимитов и сохранение транзакции.
     *
     * @param request данные транзакции ({@link TransactionCreateRequest})
     * @return информация о сохраненной транзакции ({@link TransactionResponse})
     */
    TransactionResponse saveTransaction(TransactionCreateRequest request);

    /**
     * Рассчитывает сумму транзакций за месяц для указанной категории.
     *
     * @param category категория расходов
     * @param dateTime дата в рамках нужного месяца
     * @return сумма всех транзакций за месяц в USD
     */
    BigDecimal getMonthTransactionsSum(ExpenseCategory category, OffsetDateTime dateTime);
}