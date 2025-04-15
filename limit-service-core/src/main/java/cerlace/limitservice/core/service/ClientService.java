package cerlace.limitservice.core.service;

import cerlace.limitservice.core.dto.SpendLimitCreateRequest;
import cerlace.limitservice.core.dto.SpendLimitResponse;
import cerlace.limitservice.core.dto.TransactionResponse;

import java.util.List;

/**
 * Сервис для работы с лимитами и лимитными транзакциями.
 * <p>
 * Предоставляет методы для управления лимитами расходов
 * и получения информации о транзакциях.
 */
public interface ClientService {

    /**
     * Устанавливает новый лимит расходов для указанной категории.
     *
     * @param request запрос на установку лимита ({@link SpendLimitCreateRequest})
     * @return информация об установленном лимите ({@link SpendLimitResponse})
     */
    SpendLimitResponse setSpendLimit(SpendLimitCreateRequest request);

    /**
     * Получает все установленные лимиты расходов.
     *
     * @return список всех лимитов ({@link SpendLimitResponse}), отсортированный по дате (новые первыми)
     */
    List<SpendLimitResponse> getAllSpendLimits();

    /**
     * Получает все транзакции, превысившие установленные лимиты.
     *
     * @return список транзакций ({@link TransactionResponse}) с превышением лимита,
     * отсортированный по дате (новые первыми)
     */
    List<TransactionResponse> getLimitExceededTransactions();
}
