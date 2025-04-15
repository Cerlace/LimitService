package cerlace.limitservice.persistence.repository;

import cerlace.limitservice.persistence.entity.SpendLimit;
import cerlace.limitservice.persistence.entity.Transaction;
import cerlace.limitservice.persistence.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с банковскими транзакциями.
 * <p>
 * Предоставляет методы доступа к данным транзакций, включая специализированные запросы
 * для аналитики и отчетности. Наследует базовые CRUD-операции из {@link JpaRepository}.
 *
 * @see JpaRepository
 * @see Transaction
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    /**
     * Вычисляет сумму транзакций в USD для указанной категории за период времени.
     * <p>
     * Пример использования:
     * <pre>{@code
     * OffsetDateTime start = OffsetDateTime.now().minusMonths(1);
     * OffsetDateTime end = OffsetDateTime.now();
     * Optional<BigDecimal> sum = repository.sumByCategoryAndDatetime(
     *     ExpenseCategory.PRODUCT,
     *     start,
     *     end
     * );
     * }</pre>
     *
     * @param category категория расходов (не может быть null)
     * @param start    начальная дата периода (включительно)
     * @param end      конечная дата периода (включительно)
     * @return {@link Optional} с суммой транзакций в USD для указанной категории,
     * или {@link Optional#empty()} если транзакции не найдены
     */
    @Transactional(readOnly = true)
    @Query("SELECT SUM(t.usdSum) FROM Transaction t WHERE t.expenseCategory = :category " +
            "AND t.datetime BETWEEN :start AND :end")
    Optional<BigDecimal> sumByCategoryAndDatetime(@Param("category") ExpenseCategory category,
                                                  @Param("start") OffsetDateTime start,
                                                  @Param("end") OffsetDateTime end);

    /**
     * Находит все транзакции с превышением лимита, отсортированные по дате (от новых к старым).
     * <p>
     * Возвращает транзакции вместе с соответствующими лимитами через JOIN.
     * Пример использования:
     * <pre>{@code
     * List<Transaction> exceeded = repository.findByLimitExceededTrueOrdered();
     * }</pre>
     *
     * @return список транзакций с флагом limitExceeded=true, отсортированный по убыванию даты,
     * каждая транзакция содержит связанный объект {@link SpendLimit}
     */
    @Transactional(readOnly = true)
    @Query("SELECT t, sl FROM Transaction t JOIN SpendLimit sl ON t.spendLimit.id = sl.id " +
            "WHERE t.limitExceeded = true ORDER BY t.datetime DESC")
    List<Transaction> findByLimitExceededTrueOrdered();
}
